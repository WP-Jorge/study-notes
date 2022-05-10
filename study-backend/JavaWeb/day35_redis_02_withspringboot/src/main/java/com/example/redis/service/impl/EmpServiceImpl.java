package com.example.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redis.entity.Emp;
import com.example.redis.mapper.EmpMapper;
import com.example.redis.service.EmpService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
	@Override
	public List<Emp> findAll() {
		return baseMapper.findAll();
	}
}
