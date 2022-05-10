package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.domain.User;
import org.springframework.stereotype.Service;

/**
 * 继承IService<User>可以使用里面大量常用的方法
 */
@Service
public interface UserService extends IService<User> {
	// User getUserById(Integer id);
}
