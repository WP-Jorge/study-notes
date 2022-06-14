package com.example.oauth2_demo.mapper;

import com.example.oauth2_demo.cache.RedisCache;
import com.example.oauth2_demo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author demo_team
 * @since 2021-03-06
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface UserRoleMapper extends BaseMapper<UserRole> {

}