package com.example.boxmusic.service.impl;

import com.example.boxmusic.pojo.entity.Music;
import com.example.boxmusic.mapper.MusicMapper;
import com.example.boxmusic.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

}
