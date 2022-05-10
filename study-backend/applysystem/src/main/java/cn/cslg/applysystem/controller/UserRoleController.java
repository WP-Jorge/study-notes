package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddUserRoleDTO;
import cn.cslg.applysystem.pojo.entity.UserRole;
import cn.cslg.applysystem.service.UserRoleService;
import cn.cslg.applysystem.utils.R;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/userRole")
@Validated
public class UserRoleController {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private UserRoleService userRoleService;
	
	// @Transactional(rollbackFor = Exception.class)
	// @ApiOperation("添加用户角色")
	// @PostMapping("/addUserRole")
	// public R addUserRole(@Valid @RequestBody AddUserRoleDTO addUserRoleDTO) {
	// 	try {
	// 		int i = userRoleService.addUserRole(addUserRoleDTO);
	// 		if (i > 0) {
	// 			return R.ok("添加用户角色成功");
	// 		}
	// 		return R.ok("添加用户角色失败");
	// 	} catch (DuplicateKeyException e) {
	// 		throw new DuplicateKeyException("添加用户角色失败，键名重复，请检查数据是否正确");
	// 	} catch (DataIntegrityViolationException e) {
	// 		throw new DataIntegrityViolationException("添加用户角色失败，数据完整性违规，可能是用户id错误或角色id错误，请检查数据是否正确");
	// 	}
	// }
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("保存用户角色")
	@PostMapping("/saveUserRole")
	public R saveUserRole(@RequestBody JSONObject jsonObject) {
		try {
			Integer userId = (Integer) jsonObject.get("userId");
			ArrayList<Integer> roleList = (ArrayList<Integer>) jsonObject.get("roleList");
			if (userId == null) {
				return R.ok("roleId不能为空");
			} else if (roleList == null) {
				return R.ok("apiList不能为空");
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("user_id", userId);
			boolean isRemoved = userRoleService.removeByMap(map);
			ArrayList<UserRole> userRoleList = new ArrayList<>();
			for (Integer roleId : roleList) {
				UserRole userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRoleList.add(userRole);
			}
			boolean isSaved = userRoleService.saveBatch(userRoleList);
			// 手动清除redis中的User缓存
			redisTemplate.delete("cn.cslg.applysystem.mapper.UserMapper");
			// 手动清除redis中的Role缓存（由于用户用户角色信息缓存在role表中，因为sql操作是在role的mapper中中进行的）
			redisTemplate.delete("cn.cslg.applysystem.mapper.RoleMapper");
			return R.ok("保存用户角色成功");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("保存角色权限失败，数据完整性违规，可能是角色id错误，请检查数据是否正确");
		}
	}
	
	// @Transactional(rollbackFor = Exception.class)
	// @ApiOperation("根据用户id和角色id删除用户角色")
	// @DeleteMapping("/deleteUserRoleByUserIdAndRoleId")
	// public R deleteUserRoleByUserIdAndRoleId(@PathParam("userId") Integer userId, @PathParam("roleId") Integer roleId) {
	// 	QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
	// 	queryWrapper.eq("user_id", userId).eq("role_id", roleId);
	// 	boolean isRemoved = userRoleService.remove(queryWrapper);
	// 	if (isRemoved) {
	// 		return R.ok("删除用户角色成功");
	// 	}
	// 	return R.ok("删除用户角色失败，可能是用户id或角色id错误，请检查数据是否正确");
	// }
	
}

