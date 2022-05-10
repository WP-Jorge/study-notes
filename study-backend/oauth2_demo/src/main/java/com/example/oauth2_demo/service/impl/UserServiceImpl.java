package com.example.oauth2_demo.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oauth2_demo.entity.User;
import com.example.oauth2_demo.mapper.UserMapper;
import com.example.oauth2_demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oauth2_demo.utils.BCryptPasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo_team
 * @since 2021-03-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Autowired UserService userService;
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	
	@Override
	public int addUser(User user) {
		int i = baseMapper.addUser(user);
		if (i != 0) {
			System.out.println("添加用户成功！");
		} else {
			System.out.println("添加用户失败！");
		}
		return 0;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = baseMapper.getUserByUsername(username);
		if (user == null) {
			System.out.println("该用户不存在！");
		} else {
			System.out.println("获取用户信息成功，用户信息：" + user);
		}
		return user;
	}
	
	@Override
	public Boolean checkLogin(String username, String rawPassword) {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("该用户不存在！");
		}
		// 校验密码
		String password = user.getPassword();
		boolean matches = bCryptPasswordEncoderUtil.matches(rawPassword, password);
		if (matches) {
			System.out.println("登录成功");
			return  true;
		} else {
			return false;
		}
	}
	
}
