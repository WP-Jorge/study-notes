package com.example.shiro_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shiro_springboot.entity.Perms;
import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.mapper.UserMapper;
import com.example.shiro_springboot.service.UserService;
import com.example.shiro_springboot.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	// @Autowired
	// UserService userService;
	// @Autowired
	// UserMapper userMapper;
	public int insert(User user) {
		// 处理业务调用dao
		// 1.生成随机盐
		String salt = SaltUtil.getSalt(8);
		// 2.将随机盐保存到数据库
		user.setSalt(salt);
		// 3.明文密码进行调用md5 + salt + hash散列
		Md5Hash hash = new Md5Hash(user.getPassword(), user.getSalt(), 1024);
		user.setPassword(hash.toHex());
		int i = baseMapper.insert(user);
		return i;
	}
	
	@Override
	public User getUser() {
		return baseMapper.getUser();
	}
	
	@Override
	public User findByUsername(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		User user = baseMapper.selectOne(wrapper);
		return user;
	}
	
	@Override
	// 根据用户查询所有角色
	public User findRolesByUsername(String username) {

		return baseMapper.findRolesByUsername(username);
	}
	
	@Override
	public List<Perms> findPermsByRoleId(Integer roleId) {
		return baseMapper.findPermsByRoleId(roleId);
	}
}
