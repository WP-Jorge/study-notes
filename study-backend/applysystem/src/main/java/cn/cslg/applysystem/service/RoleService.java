package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.dto.AddRoleDTO;
import cn.cslg.applysystem.pojo.entity.Role;
import cn.cslg.applysystem.pojo.vo.RoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface RoleService extends IService<Role> {
	List<String> getRolesByUsername(String username);
	
	int addRole(AddRoleDTO addRoleDTO);
	
	IPage<RoleVO> getAllRolesWithPage(Page page);
	
	List<RoleVO> getRolesByUsernameVO(String username);
	
	IPage<RoleVO> getRolesByRoleNameWithPage(Page page, String roleName);
	
}
