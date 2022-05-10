package com.example.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwt.entity.User;

public interface UserMapper extends BaseMapper<User> {
	User login(User user);
}
