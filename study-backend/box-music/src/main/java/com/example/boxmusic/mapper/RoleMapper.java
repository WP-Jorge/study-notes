package com.example.boxmusic.mapper;

import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.pojo.vo.RoleVO;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface RoleMapper extends BaseMapper<Role> {
	List<RoleVO> getRolesWithUsername(String username);
}
