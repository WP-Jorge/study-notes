package com.example.boxmusic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.dto.AddUserDTO;
import com.example.boxmusic.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.pojo.vo.UserVO;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface UserMapper extends BaseMapper<User> {
	
	User getUserWithUsername(String username);
	
	UserVO getUserInfoWithUsername(String username);
	
	int addUser(AddUserDTO addUserDTO);
	
	IPage<UserVO> getAllUsersPage(Page<Map<String, Object>> page);
	
	// IPage<UserVO> getRolesByRoleNamePage(Page<Map<String, Object>> page, String username);
	
}
