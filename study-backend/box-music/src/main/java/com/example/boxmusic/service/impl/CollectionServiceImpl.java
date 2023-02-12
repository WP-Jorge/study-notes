package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.mapper.CollectionMapper;
import com.example.boxmusic.mapper.MusicPlaylistMapper;
import com.example.boxmusic.pojo.dto.UpdateCollectionDTO;
import com.example.boxmusic.pojo.entity.Collection;
import com.example.boxmusic.pojo.entity.MusicPlaylist;
import com.example.boxmusic.pojo.vo.MusicVO;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.CollectionService;
import com.example.boxmusic.service.MusicPlaylistService;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.utils.JwtTokenUtil;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Override
	public R updateCollection(String headerToken, List<Long> musicIds) {
		try {
			if ("".equals(headerToken) || headerToken == null) {
				return R.error("token异常");
			}
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			String username = jwtTokenUtil.getUsernameFromToken(token);
			if (username == null) {
				return R.error("token异常 请重新登录");
			}
			UserVO userInfo = userService.getUserInfoWithUsername(username);
			ArrayList<UpdateCollectionDTO> collections = new ArrayList<>();
			for (Long musicId : musicIds) {
				UpdateCollectionDTO collectionDTO = new UpdateCollectionDTO();
				collectionDTO.setUserId(userInfo.getUserId());
				collectionDTO.setMusicId(musicId);
				collections.add(collectionDTO);
			}
			if (collections.size() > 0) {
				Integer i = baseMapper.insertCollections(collections);
			}
			return R.success("添加成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，该歌曲已添加至收藏");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public R getCollection(String headerToken) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		List<MusicVO> musics = baseMapper.getCollection(userInfo.getUserId());
		return R.success("pageList", musics);
	}

	@Override
	public R deleteCollection(String headerToken, List<Long> musicIds) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserVO userInfo = userService.getUserInfoWithUsername(username);
		Integer i = 0;
		if (musicIds.size() > 0) {
			i = baseMapper.deleteCollection(userInfo.getUserId(), musicIds);
		}
		if (i > 0) {
			return R.success("操作成功");
		}
		return R.error("删除失败");

	}
}
