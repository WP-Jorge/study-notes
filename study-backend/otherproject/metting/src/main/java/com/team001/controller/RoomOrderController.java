package com.team001.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.OrderDate;
import com.team001.common.VeDate;
import com.team001.common.lang.Result;
import com.team001.components.JwtTokenUtil;
import com.team001.entity.Room;
import com.team001.entity.RoomOrder;
import com.team001.entity.RoomType;
import com.team001.entity.User;
import com.team001.service.RoomOrderService;
import com.team001.service.RoomService;
import com.team001.service.UserService;
import com.team001.vo.RoomOrderView;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/room-order")
public class RoomOrderController {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    UserService userService;

    @Autowired
    RoomOrderService roomOrderService;

    @Autowired
    RoomService roomService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @ApiOperation("会议室本月使用报表")
    @GetMapping("views")
    public Result views(){
        List<RoomOrderView> roomOrderViews = roomOrderService.selectRoomList();
        Map<String, Object> map = new HashMap<>();

        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> total= new ArrayList<>();
        for (RoomOrderView roomOrderView : roomOrderViews) {
            name.add(roomOrderView.getName());
            total.add(roomOrderView.getTotal());
        }
        map.put("name",name);
        map.put("total",total);
        map.put("months",Calendar.getInstance().get(Calendar.MONTH)+1);
        return Result.success(map);
    }

    @ApiOperation("当前会议室预约情况")
    @GetMapping("/currentOrder/{roomName}")
    public Result currentOrder(@PathVariable String roomName,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit){
        Page<RoomOrder> roomOrderPage = new Page<RoomOrder>(page,limit);

        IPage<RoomOrder> orderIPage = roomOrderService.page(roomOrderPage, new QueryWrapper<RoomOrder>()
                .eq("name", roomName)
                .orderByDesc("start_time")
                .orderByDesc("status"));

        return Result.success(orderIPage);
    }
    @ApiOperation("会议室按时间段查询")
    @GetMapping("/free")
    public Result free(@RequestParam(defaultValue = "0",required = false) long startTime,
                       @RequestParam(defaultValue = "0",required = false) long endTime,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit){


        Page<Room> roomPage = new Page<Room>(page,limit);

        if(startTime ==0&& endTime==0){
            IPage<Room> roomIPage = roomService.page(roomPage, new QueryWrapper<Room>().ne("enabled", 0));
            return Result.success(roomIPage);
        }
        List<RoomOrder> roomOrderList = roomOrderService.list(new QueryWrapper<RoomOrder>().ne("status",-1));
        List<RoomOrder> orderList = null;

        orderList = roomOrderList.stream().filter((RoomOrder roomOrder) ->
                OrderDate.valid(startTime,endTime,roomOrder.getStartTime(),roomOrder.getEndTime())).collect(Collectors.toList());

        Collection<String> name=new ArrayList<String>();
        for (RoomOrder roomOrder : orderList) {
                    name.add(roomOrder.getName());
        }


        IPage<Room> page1 = null;
        if(name.size()!=0){
            page1 = roomService.page(roomPage,new QueryWrapper<Room>()
                    .ne("enabled",0).notIn("name",name));
        } else {
            page1 = roomService.page(roomPage,new QueryWrapper<Room>().ne("enabled",0));
        }

        return Result.success(page1);
    }


    @ApiOperation("会议室预约")
    @PostMapping("/order")
    @Transactional
    public Result save(@Validated @RequestBody RoomOrder roomOrder,
                       HttpServletRequest request) throws ParseException {


        Room room = roomService.getOne(new QueryWrapper<Room>().eq("name", roomOrder.getName()));
        if(room.getEnabled() == 0){
            return Result.fail("当前会议室不可用");
        }
        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        System.out.println(header);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);

        //获取用户信息
        User user = userService.getUserByUserName(usernameFromToken);
        roomOrder.setUsername(user.getName());


        String string = VeDate.longToString(roomOrder.getStartTime(), "yyyy-MM-dd HH:mm");
        String string1 = VeDate.longToString(roomOrder.getEndTime(), "yyyy-MM-dd HH:mm");


        roomOrder.setStartEndTime(string+"至"+string1.split(" ")[1]);
        roomOrder.setCreateTime(LocalDateTime.now());

        if(roomOrder.getStartTime()>=roomOrder.getEndTime() || roomOrder.getStartTime() <= LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()){
            return Result.fail(-1,"请选择正确时间段",null);
        }
        if(roomOrder.getEndTime() - roomOrder.getStartTime()> 14400000){
            return Result.fail(-2,"预约时间不能超过4个小时",null);
        }

        if((roomOrder.getStartTime()-3600000)<LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()){
            System.out.println("+#######$#$#"+roomOrder.getStartTime());
            System.out.println("+#######$#$#"+new Date().getTime());
            return Result.fail(-2,"请提前一小时进行预约",null);
        }

        QueryWrapper<RoomOrder> queryWrapper = new QueryWrapper<>();

        //获取当前会议室的预约情况
        queryWrapper.eq("name",roomOrder.getName());
        //把已经拒绝的，用户取消的，超时的去掉
        Collection<String> status=new ArrayList<String>();
        status.add("-1");
        status.add("-2");
        status.add("-3");
        queryWrapper.notIn("status",status);
        List<RoomOrder> list = null;
        if(roomOrder.getOno() == null){
            list = roomOrderService.list(queryWrapper);

        } else {
            list = roomOrderService.list(queryWrapper.ne("ono",roomOrder.getOno()));
        }
        for (RoomOrder order : list) {
            if(OrderDate.valid(roomOrder.getStartTime(),roomOrder.getEndTime(),order.getStartTime(),order.getEndTime())
            ){
                return Result.fail(-3,"当前会议时间冲突",null);
            }
        }
        boolean b = roomOrderService.saveOrUpdate(roomOrder);
        if(b){
            return Result.success(0,"预约成功",roomOrder);
        } else {
            return Result.success("预约失败");
        }
    }


    @ApiOperation("会议室预约审核")
    @PostMapping("/verify")
    @Transactional
    public Result verify(@RequestBody RoomOrder roomOrder){
        roomOrder.setVerifyTime(LocalDateTime.now());
        boolean b = roomOrderService.updateById(roomOrder);
        Room room = roomService.getOne(new QueryWrapper<Room>().eq("name", roomOrder.getName()));

        User user = userService.getOne(new QueryWrapper<User>().eq("username", roomOrder.getUsername()));
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if(b){
            if(roomOrder.getStatus() == 1){
                mailMessage.setFrom("1494135711@qq.com");
                mailMessage.setSubject("会议室预约详情");
                mailMessage.setText(user.getName()+"用户你好，你的"+ roomOrder.getStartEndTime() +"在"+room.getPosition()+"的会议已经通过预约，会议室临时密码为："+ roomOrder.getPassword());
                mailMessage.setTo(user.getEmail());
                mailSender.send(mailMessage);
          } else if(roomOrder.getStatus() == -1){
                mailMessage.setFrom("1494135711@qq.com");
                mailMessage.setSubject("会议室预约详情");
                mailMessage.setText(user.getName()+"用户你好，你的"+ roomOrder.getStartEndTime() +"在"+room.getPosition()+"的会议已经被拒绝，具体原因请到会议室预约系统查看");
                mailMessage.setTo(user.getEmail());
                mailSender.send(mailMessage);
            }
            return Result.success("更改成功");
        } else {
            return Result.fail("更改失败");
        }
    }

    @ApiOperation("我的预约")
    @GetMapping("/myorder")
    public Result order(HttpServletRequest request,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer limit,
                        @RequestParam(defaultValue = "") String name){
        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        System.out.println(header);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);
        //获取用户信息
        User user = userService.getUserByUserName(usernameFromToken);
        Page<RoomOrder> orderPage = new Page<RoomOrder>(page,limit);
        QueryWrapper<RoomOrder> queryWrapper = new QueryWrapper<RoomOrder>()
                .orderByDesc("create_time")
                .eq("username", user.getName())
                .eq("status", 0)
                .like("name",name)
                .ge("start_time",LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()+3600000);
        IPage<RoomOrder> page1 = roomOrderService.page(orderPage, queryWrapper);

        return Result.success(page1);
    }

    @ApiOperation("已审核的预约")
    @GetMapping("/passOrder")
    public Result passOrder(HttpServletRequest request,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit){
        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);
        //获取用户信息
        User user = userService.getUserByUserName(usernameFromToken);
        Page<RoomOrder> orderPage = new Page<RoomOrder>(page,limit);
        IPage<RoomOrder> page1 = roomOrderService.page(orderPage, new QueryWrapper<RoomOrder>()
                .orderByDesc("create_time")
                .eq("username", user.getName())
                .ne("status",0));
        return Result.success(page1);
    }

    @ApiOperation("预约撤销")
    @GetMapping("/cancel/{id}")
    public Result cancel(HttpServletRequest request,
                         @PathVariable("id") Integer id){
        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);

        RoomOrder roomOrder = roomOrderService.getById(id);
        User user = userService.getUserByUserName(usernameFromToken);
        if(roomOrder.getUsername().equals(user.getName())){
            roomOrder.setStatus(-2);
            boolean b = roomOrderService.updateById(roomOrder);
            if(b){
                return Result.success(0,"取消成功",null);
            }
            return Result.success(0,"取消失败",null);

        }
        System.out.println(roomOrder.getUsername());
        System.out.println(usernameFromToken);
        return Result.success(0,"当前用户和预约用户不匹配",null);

    }

    @ApiOperation("预约详情")
    @GetMapping("/orderDetail/{id}")
    public Result orderDetail(@PathVariable("id") Integer id){
        RoomOrder roomOrder = roomOrderService.getById(id);

        return Result.success(roomOrder);
    }

    @ApiOperation("未审批预约")
    @GetMapping("/list")
    public Result orderList(@RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit){


        if(page == -1){
            return Result.success(roomOrderService.list(new QueryWrapper<RoomOrder>().eq("status",0)));
        }
        Page<RoomOrder> orderPage = new Page<RoomOrder>(page,limit);
        IPage<RoomOrder> page1 = roomOrderService.page(orderPage,new QueryWrapper<RoomOrder>()
                .eq("status", 0)
                .like("name",name));
        return Result.success(page1);
    }

    @ApiOperation("已审批预约")
    @GetMapping("/passList")
    public Result passOrder(@RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit){


        if(page == -1){
            return Result.success(roomOrderService.list(new QueryWrapper<RoomOrder>().ne("status",0)));
        }
        Page<RoomOrder> orderPage = new Page<RoomOrder>(page,limit);
        IPage<RoomOrder> page1 = roomOrderService.page(orderPage, new QueryWrapper<RoomOrder>()
                .ne("status", 0)
                .like("name",name));

        return Result.success(page1);
    }
}
