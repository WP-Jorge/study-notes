package com.example.shiro_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro_springboot.entity.Perms;
import com.example.shiro_springboot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
	User findRolesByUsername(String username);
	
	User getUser();
	
	// 根据角色id查询权限集合
	List<Perms> findPermsByRoleId(Integer roleId);
}
