package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boxmusic.pojo.dto.UpdateApiDTO;
import com.example.boxmusic.pojo.entity.Api;
import com.example.boxmusic.mapper.ApiMapper;
import com.example.boxmusic.pojo.vo.ApiTreeVO;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleWithApiVO;
import com.example.boxmusic.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.R;
import org.springframework.dao.DuplicateKeyException;
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
	public List<RoleWithApiVO> getApiTreeWithUserId(Long userId) {
		return baseMapper.getApiTreeWithUserId(userId);
	}
	
	@Override
	public List<ApiVO> getApisWithUsername(String username) {
		return baseMapper.getApisWithUsername(username);
	}
	
	@Override
	public R getApiTreeByUserId(Long userId) {
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
	
	@Override
	public R getApiTree() {
		List<ApiTreeVO> apiTree = baseMapper.getApiTree();
		return R.success("获取权限树成功").put("apiTree", apiTree);
	}
	
	@Override
	public R updateApis(List<UpdateApiDTO> apis) {
		try {
			Integer integer = baseMapper.updateApis(apis);
			if (integer > 0) {
				return R.success("更新成功");
			}
			return R.error("更新失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，权限路径已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R deleteApisByApiIds(List<Long> apiIds) {
		try {
			int i = baseMapper.deleteBatchIds(apiIds);
			if (i > 0) {
				return R.success("删除成功");
			}
			return R.error("删除失败");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R updateApi(UpdateApiDTO api) {
		try {
			Long parentApiId = api.getParentApiId();
			if (parentApiId != null) {
				UpdateWrapper<Api> updateWrapper = new UpdateWrapper<>();
				updateWrapper.eq("api_id", parentApiId)
								.set("api_type", 0);
				baseMapper.update(null, updateWrapper);
			}
			Integer i = baseMapper.updateApi(api);
			if (i > 0) {
				return R.success("更新成功");
			}
			return R.error("更新失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，权限路径已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
