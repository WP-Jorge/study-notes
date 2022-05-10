package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2021-01-27
 */
public interface UserService extends IService<User> {
	User getUser(Integer id);
}
