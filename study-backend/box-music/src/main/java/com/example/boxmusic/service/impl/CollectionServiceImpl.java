package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.mapper.CollectionMapper;
import com.example.boxmusic.mapper.MusicPlaylistMapper;
import com.example.boxmusic.pojo.entity.Collection;
import com.example.boxmusic.pojo.entity.MusicPlaylist;
import com.example.boxmusic.service.CollectionService;
import com.example.boxmusic.service.MusicPlaylistService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

}
