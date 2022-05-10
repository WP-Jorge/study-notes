package com.team001.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.CopyUtils;
import com.team001.common.UserUtils;
import com.team001.common.lang.Result;
import com.team001.components.BCryptPasswordEncoderUtil;
import com.team001.components.JwtTokenUtil;
import com.team001.entity.*;
import com.team001.mapper.EasyExcel.DemoDAO;
import com.team001.mapper.EasyExcel.DemoDataListener;
import com.team001.service.*;
import com.team001.vo.UserDetail;
import com.team001.vo.UserLogin;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    @Autowired
    UserService userService;

    @Autowired
    UserLogService userLogService;

    @Autowired
    UserLockedService userLockedService;

    @Autowired
    RoomOrderService roomOrderService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @ApiOperation("用户列表")
    @PostMapping("/reset/{id}")
    @Transactional
    public Result reset(@PathVariable Integer id){
        User user = userService.getOne(new QueryWrapper<User>().eq("uno", id));
        user.setPassword(bCryptPasswordEncoderUtil.encode("000000"));
        boolean b = userService.updateById(user);
        if(b){
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }

    /**
     * 用户查询
     * @param page
     * @param limit
     * @param name
     * @param phone
     * @param email
     * @return
     */
    @ApiOperation("用户列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "") String name,
                       @RequestParam(defaultValue = "") String phone,
                       @RequestParam(defaultValue = "") String email){

        if(page == -1){
            List<User> list = userService.list();
            return Result.success(list);
        }
        Page page1 = new Page(page,limit);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.like("name",name);
        queryWrapper.like("phone",phone);
        queryWrapper.like("email",email);
        IPage data = userService.page(page1,queryWrapper);

        return Result.success(data);
    }
/*
    /**
     *
     * @param username
     * @return
     *//*
    @ApiOperation("按用户名查询用户信息")
    @GetMapping("/test/{username}")
    public Result getMeetById(@PathVariable("username") String username){

        List<Role> userRolesByName = userService.getUserRolesByName(username);
        return Result.success(userRolesByName);
    }*/

    /**
     *
     * @return
     */
    @ApiOperation("按id查询用户信息")
    @GetMapping("/get")
    public Result getUserById(HttpServletRequest request){

        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        System.out.println(header);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);
        //获取用户信息
        User user = userService.getUserDetail(usernameFromToken);

        return Result.success(user);
    }

    /**
     *
     * @return
     */
    @ApiOperation("按用户名查询用户信息")
    @GetMapping("/getDetail/{username}")
    public Result getUserByUsername(@PathVariable("username") String username){

        User userDetail = userService.getUserDetail(username);
        return Result.success(userDetail);
    }

    /**
     * 按id删除用户
     * @param id
     * @return
     */
    @ApiOperation("按id删除用户")
    @DeleteMapping("/del/{id}")
    @Transactional
    public Result delete(@PathVariable("id") Integer id){

        boolean b = userService.removeById(id);

        return  b ? Result.success("删除成功") : Result.fail("删除失败");
    }

    /**
     * 按id删除用户
     * @param user
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public Result save(@Validated @RequestBody User user){

        User userByUserName = userService.getUserByUserName(user.getUsername());
        if(userByUserName!=null){
            return Result.success(-2,"该用户已存在",null);
        }
        user.setCreateTime(LocalDateTime.now());

        user.setPassword(bCryptPasswordEncoderUtil.encode("000000"));
        boolean b = userService.save(user);
        if(b){
            return Result.success(0,"添加成功",null);
        } else {
            return Result.success(-1,"添加失败",null);
        }
    }

    @ApiOperation("用户更新手机")
    @GetMapping("/update/phone")
    @Transactional
    public Result update(@RequestParam(required = false) String phone,@RequestParam(required = false) String email,HttpServletRequest request){

        if(phone == null && email == null)
            return Result.fail("请传入参数");
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone != null){
            if(phone.length() != 11){
                return Result.fail("手机号应为11位数");
            }else{
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                if(!isMatch){
                    return Result.fail("手机号格式错误");
                }
            }
        }
        if(email != null){
            String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(regEx1);
            Matcher m = p.matcher(email);
            if(!m.matches())
                return Result.fail("邮箱格式错误");
        }
        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);
        //获取用户信息
        User user = userService.getUserDetail(usernameFromToken);
        if(email != null)
            user.setEmail(email);
        if(phone != null)
            user.setPhone(phone);
        boolean b = userService.updateById(user);
        if(b){
            return Result.success("更新成功");

        } else {
            return Result.fail("更新失败");
        }
    }


    @ApiOperation("用户更新密码")
    @GetMapping("/update/pwd")
    @Transactional
    public Result updatePwd(@RequestParam String oldPwd,@RequestParam String newPwd,HttpServletRequest request){


        if (oldPwd.length() < 6 ||newPwd.length() < 6 ||oldPwd.length() > 12 ||newPwd.length() > 12 ){
            return Result.fail("密码长度为6-12位");
        }
        //获取token
        String header = request.getHeader("Authorization").split(" ")[1];
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);
        //获取用户信息
        User user = userService.getUserDetail(usernameFromToken);

        boolean matches = bCryptPasswordEncoderUtil.matches(oldPwd,user.getPassword());
        if (matches){
            user.setPassword(bCryptPasswordEncoderUtil.encode(newPwd));
            boolean b = userService.updateById(user);
            if(b){
                return Result.success("更新成功");
            }
            return Result.fail("更新失败");

        } else {
            return Result.fail("原密码错误");

        }

    }

    @ApiOperation("更新用户详细信息")
    @PostMapping("/update")
    @Transactional
    public Result updateDetail(@RequestBody UserDetail userDetail) throws SQLException {

        User user = new User();
        userDetail.setPassword(null);
        BeanUtils.copyProperties(userDetail, user,"select");
       /* if(user.getPassword()== null){
        user.setPassword(bCryptPasswordEncoderUtil.encode("000000"));
        }*/
        if(user.getCreateTime() == null){
            user.setCreateTime(LocalDateTime.now());
        }
        boolean username = userService.saveOrUpdate(user, new QueryWrapper<User>().eq("username", user.getUsername()));
        if(!username){
            return Result.fail("更新失败");
        }
        userRoleService.remove(new QueryWrapper<UserRole>().eq("username",user.getUsername()));
        for (Object o : userDetail.getSelect()) {
            UserRole userRole = new UserRole();

            userRole.setRoleName(o.toString());
                userRole.setUsername(user.getUsername());
                try {
                    boolean b = userRoleService.saveOrUpdate(userRole);
                } catch (Exception e){
                    throw new SQLException(e.getMessage());
                }
        }
        return Result.success(0,"更新成功",null);

    }
    @ApiOperation("更新用户状态")
    @PostMapping("/update/status/{id}")
    @Transactional
    public Result updateStatus(@PathVariable("id") Integer id,@RequestParam Integer enabled){

        User user = userService.getById(id);
        user.setEnabled(enabled);
        userService.updateById(user);
        return Result.success("更新成功");
    }

    @Transactional
    @ApiOperation("用户取消预约")
    @PostMapping("/cancel/{id}")
    public Result cancel(@PathVariable("id") Integer id, HttpServletRequest request){

        //获取token
            String header = request.getHeader("Authorization");
            System.out.println(header);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(header);

        RoomOrder roomOrder = roomOrderService.getById(id);
        if(!roomOrder.getUsername().equals(usernameFromToken)) return Result.fail("权限不足");
        System.out.println(roomOrder.getUsername());
        System.out.println(usernameFromToken);
        roomOrder.setStatus(-2);
        boolean b = roomOrderService.updateById(roomOrder);
        if(b){
            return Result.success("取消成功");
        } else {
            return Result.fail("取消失败");
        }
    }

    @Transactional
    @PostMapping("/excel")
    public Result excel(MultipartFile file) throws IOException {
        List<User> list = userService.list();
        List<String> rep = UserUtils.rep(list);
        DemoDataListener demoDataListener = new DemoDataListener(userService,userRoleService);
        demoDataListener.setList1(rep);
        EasyExcel.read(file.getInputStream(), User.class, demoDataListener).sheet().doRead();

        return Result.success("添加成功");
    }

    /*@PostMapping("/login")
    public Result login(@RequestBody UserLogin userLogin){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User user = userService.getOne(new QueryWrapper<User>().eq("username", userLogin.getUsername()));
        if(user == null){
            return Result.fail("用户不存在",null);
        } else {
            UserLocked userLocked = userLockedService.getOne(new QueryWrapper<UserLocked>().eq("username", userLogin.getUsername()).orderByDesc("lock_end").last("LIMIT 1"));

            System.out.println(userLocked);
            if(userLocked!=null && userLocked.getLockEnd().compareTo(LocalDateTime.now())>=0){
                return Result.fail("用户被锁定，请稍后再试",userLocked.getLockEnd().compareTo(LocalDateTime.now()));
            }

            //日志文件添加
            UserLog userLog = new UserLog();
            userLog.setUsername(user.getUsername()).setIpaddress("localhost").setLoginTime(LocalDateTime.now());
            //密码校验
            System.out.println(DigestUtils.md5DigestAsHex(userLogin.getPassword().getBytes()));
            System.out.println("======================");
            System.out.println(user.getPassword());

            //密码验证通过
            if(DigestUtils.md5DigestAsHex(userLogin.getPassword().getBytes()).trim().equals(user.getPassword().trim())){
                userLog.setLogcontent("登陆成功");
                userLogService.save(userLog);
                user.setLogin(5);
                userService.update(user,new QueryWrapper<User>().eq("username",userLogin.getUsername()));
                return Result.success(200,"登陆成功",null);

            //密码验证失败
            } else {
                userLog.setLogcontent("登陆失败");
                userLogService.save(userLog);

                int count = user.getLogin();

                user.setLogin(count-1);
                userService.update(user,new QueryWrapper<User>().eq("username",userLogin.getUsername()));

//                int count = userLogService.count(new QueryWrapper<UserLog>().like("logcontent","登陆失败"));
                //锁定用户
                if(count <= 0){
                    UserLocked locked = new UserLocked();
                    locked.setLockStart(LocalDateTime.now()).setLockEnd(LocalDateTime.now().plusMinutes(15)).setUsername(user.getUsername());
                    userLockedService.save(locked);
                    user.setLogin(5);
                    userService.update(user,new QueryWrapper<User>().eq("username",userLogin.getUsername()));
                    return Result.fail("用户被锁定，请稍后再试",null);
                }
                return Result.success(400,"密码错误,"+count+"次后用户将被锁定",null);
            }
        }
    }*/
}
