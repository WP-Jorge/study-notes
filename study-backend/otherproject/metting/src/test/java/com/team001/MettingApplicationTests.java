package com.team001;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.CopyUtils;
import com.team001.common.VeDate;
import com.team001.components.BCryptPasswordEncoderUtil;
import com.team001.components.JwtTokenUtil;
import com.team001.entity.*;
import com.team001.mapper.EasyExcel.DemoDataListener;
import com.team001.service.*;
import com.team001.vo.RoomOrderView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MettingApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    ApiService apiService;

    @Autowired
    RoomOrderService roomOrderService;
    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    @Autowired
    RoomService roomService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    void contextLoads() {
        String username = jwtTokenUtil.getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MDA2NjM4MjQsInN1YiI6IjA5MjcxODEzMyIsImlhdCI6MTYwMDA1OTAyNDA1OH0.krru2NiZXK1Jk9YmvR28pNCHBcXN295_HKjHPDHUjeFIUPHRShHtWtcJCbKwUY7tRs9CbpUq_1tz5qZS5aY7xA");
        System.out.println(username);

    }


    //获取用户权限
    @Test
    void UserRole(){
        List<String> rolesByUserName = roleService.getRolesByUserName("092718133");

        for (String s : rolesByUserName) {
            System.out.println(s);
        }
    }

    //用户名获取用户
    @Test
    void getUserByUserName(){
        User byId = userService.getUserByUserName("092718133");
        System.out.println(byId);
    }
//用户名获取用户
    @Test
    void get(){

        User userDetail = userService.getUserDetail("092718133");
        System.out.println(userDetail);
    }
    //用户名获取接口
    @Test
    void getRoleApi(){
        List<Api> apiUrlByUserName = apiService.getApiUrlByUserName("lead");
        for (Api api : apiUrlByUserName) {
            System.out.println(api);
        }
    }

    @Test
    void Pass(){
        String admin = bCryptPasswordEncoderUtil.encode("admin");
        System.out.println(admin);
    }

    @Test
    void update(){
        User user = new User();

        User user1 = userService.getById(1);
        user.setPassword(bCryptPasswordEncoderUtil.encode("000000"));
        CopyUtils.copyProperties(user,user1);

        userService.updateById(user1);
    }

    @Test
    void order() {
        RoomOrder roomOrder = new RoomOrder();
        roomOrder.setUsername("092718133");
        roomOrder.setApplyReason("开会");
        roomOrder.setCreateTime(LocalDateTime.now());
        roomOrder.setStartTime(VeDate.dateToLong(new Date()));
        roomOrder.setEndTime(VeDate.dateToLong(new Date()));
        roomOrder.setStartEndTime(VeDate.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"到"+VeDate.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        roomOrder.setPersonNumber(100);

        roomOrderService.save(roomOrder);
       /* Date date = new Date();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String format = sdf.format(date);
        LocalDateTime parse = LocalDateTime.parse(format, dateTimeFormatter1);
        System.out.println(parse);
        System.out.println(format);*/

        /*System.out.println(VeDate.dateToLong(date1));*/
    }

    @Test
    void roomOrder(){
        QueryWrapper<RoomOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mno",1);
        List<RoomOrder> list = roomOrderService.list(queryWrapper);
        for (RoomOrder roomOrder : list) {

            System.out.println(roomOrder);
        }
    }

    @Test
    void mail(){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("1494135711@qq.com");
        mailMessage.setSubject("会议室预约详情");
        mailMessage.setText("你的17：00-18：00在7-203的会议已经通过预约，会议室临时密码为：000000");

        mailMessage.setTo("1494135711@qq.com");
        mailSender.send(mailMessage);
    }

    /**
     * 最简单的读
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
//    @Test
//    public void simpleRead() {
//        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//        // 写法1：
//        String fileName ="C:\\Users\\lenovo\\Desktop\\easy.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        List<User> list = userService.list();
//        DemoDataListener demoDataListener = new DemoDataListener();
//        EasyExcel.read(fileName, User.class,demoDataListener).sheet().doRead();
//    }

    @Value("${address}")
    private int serverPort;
    @Test
    void views(){
        System.out.println(serverPort);
    }

}
