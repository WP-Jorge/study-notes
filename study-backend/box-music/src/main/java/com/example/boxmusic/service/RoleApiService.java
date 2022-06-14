package com.example.boxmusic.service;

import com.example.boxmusic.pojo.dto.UpdateRoleApiDTO;
import com.example.boxmusic.pojo.entity.RoleApi;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface RoleApiService extends IService<RoleApi> {
	
	R updateRoleApisByRoleId(Long roleId, List<UpdateRoleApiDTO> updateRoleApiDTOList);
	
	R getApisByRoleId(Long roleId);
}
