package com.example.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mp.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
	User getUser(Integer id);
}
