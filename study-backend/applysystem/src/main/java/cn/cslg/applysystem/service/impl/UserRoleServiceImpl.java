package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.dto.AddUserRoleDTO;
import cn.cslg.applysystem.pojo.entity.UserRole;
import cn.cslg.applysystem.mapper.UserRoleMapper;
import cn.cslg.applysystem.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
	
	@Override
	public int addUserRole(AddUserRoleDTO addUserRoleDTO) {
		return baseMapper.addUserRole(addUserRoleDTO);
	}
}
