package com.example.boxmusic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.entity.Album;
import com.example.boxmusic.pojo.entity.Singer;
import com.example.boxmusic.pojo.vo.AlbumVO;
import com.example.boxmusic.pojo.vo.MusicVO;
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
public interface AlbumMapper extends BaseMapper<Album> {
	IPage<AlbumVO> getAlbumsBySingerIdPage(Page<Map<String, Object>> page, Long singerId);
	
}
