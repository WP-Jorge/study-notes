package com.team001.service;

import com.team001.entity.Role;
import com.team001.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface UserService extends IService<User> {

//    List<Role> getUserRolesByName(String username);
    User getUserByUserName(String username);

    User getUserDetail(String username);

    boolean checkLogin(String username,String rawPassword);

}
