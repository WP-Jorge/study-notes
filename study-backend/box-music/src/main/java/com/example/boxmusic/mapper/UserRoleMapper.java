package com.example.boxmusic.mapper;

import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

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

}
