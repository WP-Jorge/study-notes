package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.mapper.MusicCategoryMapper;
import com.example.boxmusic.mapper.UserPlaylistMapper;
import com.example.boxmusic.pojo.entity.MusicCategory;
import com.example.boxmusic.pojo.entity.UserPlaylist;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.MusicCategoryService;
import com.example.boxmusic.service.UserPlaylistService;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.utils.JwtTokenUtil;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.Value;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
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
