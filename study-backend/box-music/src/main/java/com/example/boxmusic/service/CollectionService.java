package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.entity.Collection;
import com.example.boxmusic.pojo.entity.MusicPlaylist;
import com.example.boxmusic.utils.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
public interface CollectionService extends IService<Collection> {

	R updateCollection(String headerToken, List<Long> musicIds);

	R getCollection(String headerToken);

	R deleteCollection(String headerToken, List<Long> musicIds);
}
