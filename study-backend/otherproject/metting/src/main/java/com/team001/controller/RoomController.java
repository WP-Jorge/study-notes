package com.team001.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.CopyUtils;
import com.team001.common.FileUploadUtils;
import com.team001.common.OrderDate;
import com.team001.common.lang.Result;
import com.team001.entity.Room;
import com.team001.entity.RoomOrder;
import com.team001.entity.User;
import com.team001.service.RoomOrderService;
import com.team001.service.RoomService;
import com.team001.vo.RoomVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    private static final String PATH = "C:\\PIC\\";
    private static final String ADDRESS = "http://localhost:";

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    RoomService roomService;

    @Autowired
    RoomOrderService roomOrderService;

    @ApiOperation(value = "可用会议室列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "") Integer tno,
                       @RequestParam(defaultValue = "") String name){
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enabled",1);
        return getList(page,limit,tno,name,queryWrapper);

    }

    @ApiOperation(value = "全部会议室列表")
    @GetMapping("/allList")
    public Result AllList(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "") Integer tno,
                       @RequestParam(defaultValue = "") String name){

        if(page==-1){
            return Result.success(roomService.list());
        }
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        return getList(page,limit,tno,name,queryWrapper);

    }
    private Result getList(Integer page,Integer limit,Integer tno,String name,QueryWrapper<Room> queryWrapper){
        Page<Room> roomPage = new Page<>(page, limit);
        if (tno != null){
            queryWrapper.eq("tno",tno);
        }
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("create_time");
        IPage<Room> pageData = roomService.page(roomPage, queryWrapper);
        return Result.success(pageData);
    }

    @ApiOperation("按id查询会议室")
    @GetMapping("/get/{id}")
    public Result getMeetById(@PathVariable("id") Integer id){

        Room data = roomService.getById(id);
        return Result.success(data);
    }


    /**
     *
     * @param id 会议室编号
     * @return
     */
    @ApiOperation("按id删除会议室")
    @DeleteMapping("/del/{id}")
    @Transactional
    public Result delete(@PathVariable("id") Integer id){

        boolean b = roomService.removeById(id);
        if(b){
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }


    @ApiOperation("更新会议室状态")
    @PostMapping("/update/status/{id}")
    @Transactional
    public Result updateStatus(@PathVariable("id") Integer id,@RequestParam Integer enabled){

        Room room = roomService.getById(id);
        room.setEnabled(enabled);
        boolean b = roomService.updateById(room);
        if(b){
            return Result.success("更新成功");

        } else {
            return Result.fail("更新失败");
        }
    }

    /**
     *
     * @param room 会议室实体
     * @return
     */
    @ApiOperation("会议室上传或更新")
    @PostMapping("/add")
    @Transactional
    public Result save(Room room,@RequestParam(required = false,value = "file") MultipartFile fileData){

        Room temp = null;
        /*id存在表示更新会议室*/
        if(room.getMno()!=null){
            temp = roomService.getById(room.getMno());
        } else { //id不存在，创建会议室
            temp = new Room();
            temp.setCreateTime(LocalDateTime.now());
            //其他数据由前端传过来
            if(fileData == null){
                return Result.fail("文件空空如也~~");
            }
        }

        if(fileData !=null){

            try {
                // 通过ImageReader来解码这个file并返回一个BufferedImage对象
                // 如果找不到合适的ImageReader则会返回null，我们可以认为这不是图片文件
                // 或者在解析过程中报错，也返回false
                Image image = ImageIO.read(fileData.getInputStream());

                if(image == null){
                    return Result.fail("上传的不是图片");
                }

            } catch(IOException ex) {

                return Result.fail("上传文件出错");

            }


            String fileName = FileUploadUtils.upload(fileData, PATH);
            String filename = ADDRESS+this.serverPort + fileName;
            room.setPic(filename);
        }
        CopyUtils.copyProperties(room,temp);
        boolean b = roomService.saveOrUpdate(temp);
        if(b){
            return Result.success(0,"保存成功",temp);
        } else {
            return Result.fail(-1,"保存失败",temp);
        }
    }

    @ApiOperation("获取图片")
    @GetMapping("/pic/{fileName}")
    public void getPic(HttpServletResponse response, @PathVariable("fileName") String name) throws IOException {
        String suffixName = name.substring(name.indexOf("."));
        response.setContentType("image/"+suffixName+";charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(PATH).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }

    @ApiOperation("根据时间查询可预定会议室")
    @GetMapping("/getRoomByTime")
    public Result getRoomByTime(@RequestParam long start,@RequestParam long end){
        List<RoomOrder> list = roomOrderService.list(new QueryWrapper<RoomOrder>().eq("enabled",1));
        Map<String, Object> map = new HashMap<>();
        ArrayList<RoomOrder> rooms = new ArrayList<>();
        for (RoomOrder roomOrder : list) {
            if(!OrderDate.valid(start,end,roomOrder.getStartTime(),roomOrder.getEndTime())){
                rooms.add(roomOrder);
            }
        }
        return Result.success(0,"查询成功",rooms);
    }

    @ApiOperation("当前会议室预约情况")
    @GetMapping("/test/{id}")
    public Result getOrder(@PathVariable Integer id){

        Map<String, Object> map = new HashMap<>();

   /*     map.put("room",roomService.getRoomById(id));*/
        QueryWrapper<RoomOrder> queryWrapper = new QueryWrapper<>();

        //获取当前会议室的预约情况
        queryWrapper.eq("mno",id);
        List<RoomOrder> list = roomOrderService.list(queryWrapper);
        map.put("roomOrder",list);
        return Result.success(map);
    }
}
