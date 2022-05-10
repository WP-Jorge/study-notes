package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddApiDTO;
import cn.cslg.applysystem.pojo.dto.UpdateApiDTO;
import cn.cslg.applysystem.pojo.vo.ApiTreeVO;
import cn.cslg.applysystem.pojo.vo.RoleWithApiVO;
import cn.cslg.applysystem.service.ApiService;
import cn.cslg.applysystem.utils.ApiTreeUtils;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.ApiVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	// @ApiOperation("根据用户名获取权限")
	// @GetMapping("/getApisByUsername")
	// public R getApisByUsername(@PathParam("username") String username) {
	// 	List<ApiVO> apis = apiService.getApisByUsername(username);
	// 	if (apis.size() != 0) {
	// 		return R.ok("apis", Collections.singletonList(apis));
	// 	} else {
	// 		return R.ok("暂无该用户api信息");
	// 	}
	// }
	
	// @ApiOperation("根据用户名获取权限")
	// @GetMapping("/getApisByUsernameWithPage")
	// public R getApisByUsernameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	//                                    @RequestParam(defaultValue = "10") Integer pageSize,
	//                                    @PathParam("username") String username) {
	// 	Page<Map> page = new Page<>(currentPage, pageSize);
	// 	IPage<ApiVO> apis = apiService.getApisByUsernameWithPage(page, username);
	// 	System.out.println(apis);
	// 	return R.ok("apis", Collections.singletonList(apis));
	// }
	
	@ApiOperation("获取全部权限")
	@GetMapping("/getAllApisWithPage")
	public R getAllApisWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                            @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		IPage<ApiVO> apis = apiService.getAllApisWithPage(page);
		return R.ok("apis", Collections.singletonList(apis));
	}
	
	/*@ApiOperation("获取所有角色的权限树")
	@GetMapping("/getAllRolesApiTree")
	public R getAllRolesApiTree() {
		List<RoleWithApiVO> roleWithApiTree = apiService.getAllRolesApiTree();
		HashMap<String, Object> map = new HashMap<>();
		map.put("tree", roleWithApiTree);
		return R.ok(map);
	}*/
	
	@ApiOperation("获取所有权限的权限树")
	@GetMapping("/getApiTree")
	public R getApiTree() {
		List<ApiTreeVO> apiTree = apiService.getApiTree();
		HashMap<String, Object> map = new HashMap<>();
		map.put("tree", apiTree);
		return R.ok(map);
	}
	
	/*@ApiOperation("根据角色id获取权限树")
	@GetMapping("/getApiTreeByRoleId")
	public R getApiTreeByRoleId(@RequestParam Integer roleId) {
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByRoleId(roleId);
		HashMap<String, Object> map = new HashMap<>();
		map.put("tree", apiTree);
		return R.ok(map);
	}*/
	
	/*@ApiOperation("根据用户id获取权限树")
	@GetMapping("/getApiTreeByUserId")
	public R getApiTreeByUserId(@RequestParam Integer userId) {
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByUserId(userId);
		HashMap<String, Object> map = new HashMap<>();
		map.put("tree", apiTree);
		return R.ok(map);
	}*/
	
	/*@ApiOperation("根据用户名获取权限树")
	@GetMapping("/getApiTreeByUsername")
	public R getApiTreeByUsername(@RequestParam String username) {
		List<RoleWithApiVO> apiTree = apiService.getApiTreeByUsername(username);
		HashMap<String, Object> map = new HashMap<>();
		map.put("tree", apiTree);
		return R.ok(map);
	}*/
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据权限id删除权限（如果该权限下有子权限，则子权限也将被删除）")
	@DeleteMapping("/deleteApiByApiId")
	public R deleteApiByApiId(@RequestParam Integer apiId) {
		
		ArrayList<Integer> apiList = new ArrayList<>();
		List<ApiTreeVO> tree = apiService.getApiTreeByApiId(apiId);
		ApiTreeUtils.getApiId(tree, apiList);
		boolean isRemoved = apiService.removeByIds(apiList);
		if (isRemoved) {
			return R.ok("删除权限成功");
		}
		
		// boolean isDeleted = apiService.removeById(apiId);
		// if (isDeleted) {
		// 	return R.ok("删除权限成功");
		// }
		return R.ok("删除权限失败");
	}
	
	// 有问题
	// @Transactional(rollbackFor = Exception.class)
	// @ApiOperation("根据权限id批量删除权限（如果该权限下有子权限，则子权限也将被删除）")
	// @DeleteMapping("/deleteApisByApiIds")
	// public R deleteApisByApiIds(@RequestBody List<Integer> apiIds) {
	//
	// 	ArrayList<Integer> apiList = new ArrayList<>();
	// 	for (Integer apiId : apiIds) {
	// 		List<ApiTreeVO> tree = apiService.getApiTreeByApiId(apiId);
	// 		ApiTreeUtils.getApiId(tree, apiList);
	// 	}
	// 	boolean isRemoved = apiService.removeByIds(apiList);
	// 	System.out.println(apiList);
	// 	if (isRemoved) {
	// 		return R.ok("批量删除权限成功");
	// 	}
	//
	// 	// boolean isDeleted = apiService.removeById(apiId);
	// 	// if (isDeleted) {
	// 	// 	return R.ok("删除权限成功");
	// 	// }
	// 	return R.ok("批量删除权限失败");
	// }
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加权限")
	@PostMapping("/addApi")
	public R addApi(@RequestBody AddApiDTO addApiDTO) {
		try {
			int i = apiService.addApi(addApiDTO);
			if (i > 0) {
				return R.ok("添加权限成功");
			}
			return R.ok("添加权限失败，可能是parentApiId不存在，请检查数据是否正确");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加权限失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("修改权限")
	@PostMapping("/updateApi")
	public R updateApi(@RequestBody UpdateApiDTO updateApiDTO) {
		try {
			Integer apiId = updateApiDTO.getApiId();
			Integer parentApiId = updateApiDTO.getParentApiId();
			if (apiId == parentApiId) {
				return R.ok("修改失败，不能将自身的apiId设置为自己的parentApiId");
			}
			int i = apiService.updateApi(updateApiDTO);
			if (i > 0) {
				return R.ok("修改权限成功");
			}
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加权限失败，键名重复，请检查数据是否正确");
		}
		return R.ok("修改权限失败，可能是parentApiId不存在，请检查数据是否正确");
	}
	
	@ApiOperation("根据权限名称模糊查询权限")
	@GetMapping("/getApisByApiNameWithPage")
	public R getApisByApiNameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                            @RequestParam(defaultValue = "10") Integer pageSize,
	                                  String apiName) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		IPage<ApiVO> apis = apiService.getApisByApiNameWithPage(page, apiName);
		return R.ok("apis", Collections.singletonList(apis));
	}
}

