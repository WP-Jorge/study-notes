package com.example.boxmusic.service;

import com.example.boxmusic.pojo.dto.UpdateApiDTO;
import com.example.boxmusic.pojo.entity.Api;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleWithApiVO;
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
public interface ApiService extends IService<Api> {
	
	List<RoleWithApiVO> getApiTreeWithUserId(Long userId);
	
	List<ApiVO> getApisWithUsername(String username);
	
	R getApiTreeByUserId(Long userId);
	
	R getApisByUsername(String username);
	
	R getApiTree();
	
	R updateApis(List<UpdateApiDTO> apis);
	
	R deleteApisByApiIds(List<Long> apiIds);
	
	R updateApi(UpdateApiDTO api);
	
}
