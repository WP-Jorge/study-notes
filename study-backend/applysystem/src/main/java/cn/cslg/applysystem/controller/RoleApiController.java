package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.SaveRoleApiDTO;
import cn.cslg.applysystem.pojo.entity.RoleApi;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import cn.cslg.applysystem.service.RoleApiService;
import cn.cslg.applysystem.utils.R;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/roleApi")
public class RoleApiController {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	RoleApiService roleApiService;
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("保存角色权限")
	@PostMapping("/saveRoleApi")
	public R saveRoleApi(@RequestBody JSONObject jsonObject) {
		try {
			Integer roleId = (Integer) jsonObject.get("roleId");
			ArrayList<Integer> apiList = (ArrayList<Integer>) jsonObject.get("apiList");
			if (roleId == null) {
				return R.ok("roleId不能为空");
			} else if (apiList == null) {
				return R.ok("apiList不能为空");
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("role_id",roleId);
			boolean isRemoved = roleApiService.removeByMap(map);
			ArrayList<RoleApi> roleApiList = new ArrayList<>();
			apiList.forEach(apiId -> {
				RoleApi roleApi = new RoleApi();
				roleApi.setRoleId(roleId);
				roleApi.setApiId(apiId);
				roleApiList.add(roleApi);
			});
			boolean isSaved = roleApiService.saveBatch(roleApiList);
			// 手动清除redis中的RoleMapper缓存
			redisTemplate.delete("cn.cslg.applysystem.mapper.RoleMapper");
			return R.ok("保存角色权限成功");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("保存角色权限失败，数据完整性违规，可能是权限id错误，请检查数据是否正确");
		}
	}
	
	@ApiOperation("根据角色id获取权限")
	@GetMapping("/getApisByRoleId")
	public R getApisByRoleId(@PathParam("roleId") Integer roleId) {
		List<ApiVO> apiList= roleApiService.getApisByRoleId(roleId);
		return R.ok("apiList", Collections.singletonList(apiList));
	}
}

