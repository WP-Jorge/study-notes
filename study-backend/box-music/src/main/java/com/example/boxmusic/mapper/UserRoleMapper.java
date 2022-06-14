package com.example.boxmusic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.pojo.vo.UserWithRoleVO;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {
	IPage<UserWithRoleVO> getUserRolesWithUsernamePage(Page<Map<String, Object>> page, String username);
}
