package com.team001.mapper.EasyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.team001.common.UserUtils;
import com.team001.components.BCryptPasswordEncoderUtil;
import com.team001.entity.User;
import com.team001.entity.UserRole;
import com.team001.service.UserRoleService;
import com.team001.service.UserService;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Data
public class DemoDataListener extends AnalysisEventListener<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<User> list = new ArrayList<User>();
    List<String> list1 =new ArrayList<String>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private UserService userService;
    private UserRoleService userRoleService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param userService
     */
    public DemoDataListener(UserService userService,UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @SneakyThrows
    @Override
    public void invoke(User data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
             list = UserUtils.rep(list, list1);
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() throws SQLException {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//        userService.save(list);
        list.stream().forEach(user -> {
            user.setPassword(new BCryptPasswordEncoder().encode("000000"));
        });
        userService.saveBatch(list);
        List<UserRole> arr = list.stream().map(x->{
            UserRole userRole = new UserRole();
            userRole.setUsername(x.getUsername()).setRoleName("用户");
            return userRole;
        }).collect(Collectors.toList());
        userRoleService.saveBatch(arr);

        LOGGER.info("存储数据库成功！");
    }
}