package com.example.boxmusic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.entity.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MusicMapper extends BaseMapper<Music> {
	IPage<MusicVO> getMusicsByMusicTitlePage(Page<Map<String, Object>> page, String musicTitle);
	IPage<MusicVO> getMusicsByTotalViewsSortPage(Page<Map<String, Object>> page);
	IPage<MusicVO> getMusicsByCreateTimeSortPage(Page<Map<String, Object>> page);
	IPage<MusicVO> getMusicsByCategoryIdPage(Page<Map<String, Object>> page, Long categoryId);
	IPage<MusicVO> getMusicsByPlaylistIdPage(Page<Map<String, Object>> page, Long playlistId);
	IPage<MusicVO> getMusicsByAlbumIdPage(Page<Map<String, Object>> page, Long albumId);
}
