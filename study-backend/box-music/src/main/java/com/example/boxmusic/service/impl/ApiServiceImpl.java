package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boxmusic.pojo.entity.Api;
import com.example.boxmusic.mapper.ApiMapper;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleWithApiVO;
import com.example.boxmusic.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.R;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {
	@Override
	public List<RoleWithApiVO> getApiTreeWithUserId(Integer userId) {
		return baseMapper.getApiTreeWithUserId(userId);
	}
	
	@Override
	public List<ApiVO> getApisWithUsername(String username) {
		return baseMapper.getApisWithUsername(username);
	}
	
	@Override
	public R getApiTreeByUserId(Integer userId) {
		List<RoleWithApiVO> apiTree = this.getApiTreeWithUserId(userId);
		HashMap<String, Object> map = new HashMap<>();
		map.put("tree", apiTree);
		return R.success(map);
	}
	
	@Override
	public R getApisByUsername(String username) {
		List<ApiVO> apis = this.getApisWithUsername(username);
		if (apis.size() != 0) {
			return R.success("apis", Collections.singletonList(apis));
		} else {
			return R.error("暂无该用户api信息");
		}
	}
	
}
