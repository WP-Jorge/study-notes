package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.dto.AddUserRoleDTO;
import cn.cslg.applysystem.pojo.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface UserRoleService extends IService<UserRole> {
	int addUserRole(AddUserRoleDTO addUserRoleDTO);
}
