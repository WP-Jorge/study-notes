package com.example.shirojwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shirojwt.entity.Perms;
import com.example.shirojwt.entity.User;
import com.example.shirojwt.mapper.UserMapper;
import com.example.shirojwt.service.UserService;
import com.example.shirojwt.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
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
	
	@Override
	public Boolean login(User user) {
		User userDB = findByUsername(user.getUsername());
		if (userDB != null) {
			Md5Hash hash = new Md5Hash(user.getPassword(), userDB.getSalt(), 1024);
			System.out.println(hash.toHex());
			System.out.println(userDB.getPassword());
			return hash.toHex().equals(userDB.getPassword());
		} else {
			return false;
		}
	}
}
