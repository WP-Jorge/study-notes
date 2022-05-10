package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.dto.AddRoleDTO;
import cn.cslg.applysystem.pojo.entity.Role;
import cn.cslg.applysystem.mapper.RoleMapper;
import cn.cslg.applysystem.service.RoleService;
import cn.cslg.applysystem.pojo.vo.RoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	
	
	@Override
	public List<String> getRolesByUsername(String username) {
		List<String> rolesByUsername = baseMapper.getRolesByUsername(username);
		return rolesByUsername;
	}
	
	@Override
	public int addRole(AddRoleDTO addRoleDTO) {
		return baseMapper.addRole(addRoleDTO);
	}
	
	@Override
	public IPage<RoleVO> getAllRolesWithPage(Page page) {
		return baseMapper.getAllRolesWithPage(page);
	}
	
	@Override
	public List<RoleVO> getRolesByUsernameVO(String username) {
		return baseMapper.getRolesByUsernameVO(username);
	}
	
	@Override
	public IPage<RoleVO> getRolesByRoleNameWithPage(Page page, String roleName) {
		return baseMapper.getRolesByRoleNameWithPage(page, roleName);
	}
	
}
