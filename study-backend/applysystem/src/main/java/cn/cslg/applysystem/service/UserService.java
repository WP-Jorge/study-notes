package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.dto.AddUserDTO;
import cn.cslg.applysystem.pojo.dto.UpdatePasswordDTO;
import cn.cslg.applysystem.pojo.dto.UpdateUserDTO;
import cn.cslg.applysystem.pojo.entity.User;
import cn.cslg.applysystem.pojo.vo.UserWithRoleVO;
import cn.cslg.applysystem.pojo.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface UserService extends IService<User> {
	
	User getUserByUsername(String username);
	
	Boolean checkLogin(String username, String rawPassword, Integer isAdmin);
	
	UserVO getUserInfoByUsername(String username);
	
	IPage<UserWithRoleVO> getAllUsersWithPage(Page<Map> page);
	
	List<UserVO> getAllUsers();
	
	int addUser(AddUserDTO addUserDTO);
	
	int updateUser(UpdateUserDTO updateUserDTO);
	
	String getPasswordByUsername(String username);
	
	int updatePassword(UpdatePasswordDTO updatePasswordDTO);
	
	IPage<UserWithRoleVO> getUsersByUsernameWithPage(Page<Map> page, String username);
	
}
