package com.example.oauth2_demo.service;

import com.example.oauth2_demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo_team
 * @since 2021-03-06
 */
public interface UserService extends IService<User> {
	int addUser(User user);
	User getUserByUsername(String username);
	Boolean checkLogin(String username, String rawPassword);
}
