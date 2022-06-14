package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddRoleDTO;
import com.example.boxmusic.pojo.dto.UpdateRoleDTO;
import com.example.boxmusic.pojo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.vo.RoleVO;
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
public interface RoleService extends IService<Role> {
	List<RoleVO> getRolesWithUsername(String username);
	
	R getRolesByRoleNamePage(Page<Map<String, Object>> page, String roleName);
	
	R deleteRolesByRoleIds(List<Long> roleIds);
	
	R addUser(AddRoleDTO addRoleDTO);
	
	R updateRole(UpdateRoleDTO updateRoleDTO);
}
