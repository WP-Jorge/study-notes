package com.example.boxmusic.mapper;

import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.dto.UpdateRoleApiDTO;
import com.example.boxmusic.pojo.entity.RoleApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.pojo.vo.ApiVO;
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
public interface RoleApiMapper extends BaseMapper<RoleApi> {
	Integer insertRoleApis(List<UpdateRoleApiDTO> updateRoleApiDTOList);
	
	List<ApiVO> getApisWithRoleId(Long roleId);
	
}
