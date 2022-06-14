package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boxmusic.pojo.dto.UpdateRoleApiDTO;
import com.example.boxmusic.pojo.entity.RoleApi;
import com.example.boxmusic.mapper.RoleApiMapper;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.service.RoleApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class RoleApiServiceImpl extends ServiceImpl<RoleApiMapper, RoleApi> implements RoleApiService {
	
	@Override
	public R updateRoleApisByRoleId(Long roleId, List<UpdateRoleApiDTO> updateRoleApiDTOList) {
		QueryWrapper<RoleApi> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id", roleId);
		baseMapper.delete(queryWrapper);
		if (updateRoleApiDTOList.size() != 0) {
			Integer i = baseMapper.insertRoleApis(updateRoleApiDTOList);
		}
		return R.success("更新成功");
	}
	
	@Override
	public R getApisByRoleId(Long roleId) {
		List<ApiVO> apis= baseMapper.getApisWithRoleId(roleId);
		return R.success("获取角色权限成功").put("apis", apis);
	}
}
