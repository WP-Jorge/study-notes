package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.dto.AddUserDTO;
import cn.cslg.applysystem.pojo.dto.UpdatePasswordDTO;
import cn.cslg.applysystem.pojo.dto.UpdateUserDTO;
import cn.cslg.applysystem.pojo.entity.User;
import cn.cslg.applysystem.pojo.vo.UserWithRoleVO;
import cn.cslg.applysystem.pojo.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */

@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface UserMapper extends BaseMapper<User> {
	User getUserByUsername(String username);
	
	Boolean checkLogin(String username, String rawPassword, Integer isAdmin);
	
	// @Select("select * from user where username = #{username}")
	UserVO getUserInfoByUsername(String username);
	
	IPage<UserWithRoleVO> getAllUsersWithPage(Page<Map> page);
	
	List<UserVO> getAllUsers();
	
	int addUser(AddUserDTO addUserDTO);
	
	int updateUser(UpdateUserDTO updateUserDTO);
	
	String getPasswordByUsername(String username);
	
	int updatePassword(UpdatePasswordDTO updatePasswordDTO);
	
	IPage<UserWithRoleVO> getUsersByUsernameWithPage(Page<Map> page, String username);
	
	
}
