package com.example.boxmusic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.cache.RedisCache;
import com.example.boxmusic.pojo.entity.Playlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boxmusic.pojo.vo.MusicVO;
import com.example.boxmusic.pojo.vo.PlaylistVO;
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
public interface PlaylistMapper extends BaseMapper<Playlist> {
	IPage<PlaylistVO> getPlaylistsByPlaylistNamePage(Page<Map<String, Object>> page, String playlistName);
}
