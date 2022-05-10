package com.example.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.redis.entity.Emp;

import java.util.List;

public interface EmpService extends IService<Emp> {
	List<Emp> findAll();
}
