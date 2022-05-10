package com.example.oauth2_demo.service;

import com.example.oauth2_demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo_team
 * @since 2021-03-06
 */
public interface RoleService extends IService<Role> {
	List<String> getRolesByUsername(String username);
}
