package com.example.mps.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mps.pojo.User;

public interface Userdao extends BaseMapper<User> {
	User getUser(Integer id);
}
