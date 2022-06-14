package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.utils.R;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
public interface UserRoleService extends IService<UserRole> {
	
	R getUserRolesByUsernamePage(Page<Map<String, Object>> page, String username);
	
	R updateUserRolesByUserId(Long userId, List<Long> roleIds);
	
	Boolean addUserRoles(Long userId, List<Long> roleIds);
}
