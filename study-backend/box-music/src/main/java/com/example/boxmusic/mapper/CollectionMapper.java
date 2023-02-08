package com.example.boxmusic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.dto.UpdateCollectionDTO;
import com.example.boxmusic.pojo.entity.Collection;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.MusicVO;
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
public interface CollectionMapper extends BaseMapper<Collection> {
	Integer insertCollections(List<UpdateCollectionDTO> updateCollectionDTOList);

	List<MusicVO> getCollection(Long userId);

	Integer deleteCollection(Long userId, List<Long> musicIds);
}
