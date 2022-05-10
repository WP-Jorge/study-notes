package com.example.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.redis.entity.Emp;

import java.util.List;

public interface EmpMapper extends BaseMapper<Emp> {
	List<Emp> findAll();
}
