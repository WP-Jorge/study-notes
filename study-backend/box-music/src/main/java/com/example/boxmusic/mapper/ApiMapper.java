package com.example.boxmusic.mapper;

import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.dto.UpdateApiDTO;
import com.example.boxmusic.pojo.entity.Api;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.pojo.vo.ApiTreeVO;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleWithApiVO;
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
public interface ApiMapper extends BaseMapper<Api> {
	
	List<RoleWithApiVO> getApiTreeWithUserId(Long userId);
	
	List<ApiVO> getApisWithUsername(String username);
	
	List<ApiTreeVO> getApiTree();
	
	Integer updateApis(List<UpdateApiDTO> updateApiDTOList);
	
	Integer updateApi(UpdateApiDTO api);
	
}
