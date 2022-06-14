package com.example.oauth2_demo.service;

import com.example.oauth2_demo.entity.Permission;
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
public interface PermissionService extends IService<Permission> {
	List<Permission> getPermissionByUsername(String username);
}