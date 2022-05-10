package com.example.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
	// User findRolesByUsername(String username);
	
	User getUser(Integer id);
}
