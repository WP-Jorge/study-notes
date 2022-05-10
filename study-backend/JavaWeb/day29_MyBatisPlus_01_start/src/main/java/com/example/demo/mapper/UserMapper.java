package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上继承基本的接口 BaseMapper
@Repository
public interface UserMapper extends BaseMapper<User> {
	// 所有的crud操作已经编写完成了
	// 继承了BaseMapper,所有方法都来自自己的父类，我们也可以编写自己的方法
}
