package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.mapper.MusicCategoryMapper;
import com.example.boxmusic.mapper.UserPlaylistMapper;
import com.example.boxmusic.pojo.entity.MusicCategory;
import com.example.boxmusic.pojo.entity.UserPlaylist;
import com.example.boxmusic.service.MusicCategoryService;
import com.example.boxmusic.service.UserPlaylistService;
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
public class UserPlaylistServiceImpl extends ServiceImpl<UserPlaylistMapper, UserPlaylist> implements UserPlaylistService {

}