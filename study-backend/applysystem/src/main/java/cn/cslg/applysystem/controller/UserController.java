package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddUserDTO;
import cn.cslg.applysystem.pojo.dto.UpdatePasswordDTO;
import cn.cslg.applysystem.pojo.dto.UpdateUserDTO;
import cn.cslg.applysystem.service.RoleService;
import cn.cslg.applysystem.service.UserService;
import cn.cslg.applysystem.service.auth.MyUserDetails;
import cn.cslg.applysystem.utils.*;
import cn.cslg.applysystem.pojo.vo.RoleVO;
import cn.cslg.applysystem.pojo.vo.UserWithRoleVO;
import cn.cslg.applysystem.pojo.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	MyUserDetails userDetails;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	
	@ApiOperation("退出登录")
	@PostMapping("/logout")
	public R logout(HttpServletRequest httpServletRequest) {
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		if (!("".equals(headerToken) || headerToken == null)) {
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			String username = jwtTokenUtil.getUsernameFromToken(token);
			Boolean isSuccess = false;
			if (username != null) {
				isSuccess = redisUtil.deleteToken(username);
			}
			if (isSuccess) {
				return R.ok("退出登录成功");
			} else {
				return R.ok("退出登录失败");
			}
		}
		return R.ok("token异常");
	}
	
	@ApiOperation("根据token获取用户信息")
	@GetMapping("/getUserInfo")
	public R getUserInfo(HttpServletRequest httpServletRequest) {
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		HashMap<String, Object> map = new HashMap<>();
		
		if (!("".equals(headerToken) || headerToken == null)) {
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			
			String username = jwtTokenUtil.getUsernameFromToken(token);
			List<String> roles = roleService.getRolesByUsername(username);
			
			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			for (String role : roles) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
			
			userDetails.setUsername(username);
			userDetails.setAuthorities(authorities);
			
			// 判断缓存中是否有token 有则刷新token，没有则生成
			token = redisUtil.getToken(userDetails);
			if (token != null) {
				token = redisUtil.refreshToken(userDetails, token);
			} else {
				token = jwtTokenUtil.generateToken(userDetails);
				redisUtil.pushToken(userDetails, token);
			}
			
			// 将token放入map中
			UserVO userInfo = userService.getUserInfoByUsername(userDetails.getUsername());
			map.put("username", userDetails.getUsername());
			map.put("auth", userDetails.getAuthorities());
			map.put("userInfo", userInfo);
			map.put("token", token);
			return R.ok(map);
		}
		return R.ok("token异常！");
	}
	
	@ApiOperation("获取全部用户")
	@GetMapping("/getAllUsersWithPage")
	public R getAllUsersWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                     @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<Map>(currentPage, pageSize);
		IPage<UserWithRoleVO> users = userService.getAllUsersWithPage(page);
		// HashMap<String, Object> map = new HashMap<>();
		// map.put("users", users);
		return R.ok("users", Collections.singletonList(users));
	}
	
	@ApiOperation("根据用户名获取用户信息")
	@GetMapping("/getUserInfoByUsername")
	public R getUserInfoByUsername(@PathParam("username") String username) {
		List<RoleVO> roles = roleService.getRolesByUsernameVO(username);
		HashMap<String, Object> map = new HashMap<>();
		// 将token放入map中
		UserVO userInfo = userService.getUserInfoByUsername(username);
		// map.put("username", username);
		map.put("auth", roles);
		map.put("userInfo", userInfo);
		return R.ok(map);
		
		// UserVO userInfo = userService.getUserInfoByUsername(username);
		// if (userInfo != null) {
		// 	HashMap<String, Object> map = new HashMap<>();
		// 	map.put("userInfo", userInfo);
		// 	return R.ok(map);
		// } else {
		// 	return R.ok("暂无该用户信息");
		// }
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加用户")
	@PostMapping("/addUser")
	public R addUser(@Valid @RequestBody AddUserDTO addUserDTO) {
		try {
			int count = userService.addUser(addUserDTO);
			if (count > 0) {
				return R.ok("添加用户成功");
			}
			return R.ok("添加用户失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加角色失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是专业id错误，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新用户信息（管理员）")
	@PostMapping("/updateUser")
	public R updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
		try {
			int count = userService.updateUser(updateUserDTO);
			if (count > 0) {
				return R.ok("更新用户信息成功");
			}
			return R.ok("更新用户信息失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是专业id错误，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("修改密码")
	@PostMapping("/updatePassword")
	public R updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO, HttpServletRequest httpServletRequest) {
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		if (!"".equals(headerToken)) {
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			String username = jwtTokenUtil.getUsernameFromToken(token);
			updatePasswordDTO.setUsername(username);
			String enPassword = userService.getPasswordByUsername(username);
			String rawPassword = updatePasswordDTO.getRawPassword();
			boolean isMatched = bCryptPasswordEncoderUtil.matches(rawPassword, enPassword);
			if (isMatched) {
				String password = updatePasswordDTO.getPassword();
				String enNewPassword = bCryptPasswordEncoderUtil.encode(password);
				updatePasswordDTO.setPassword(enNewPassword);
				if (rawPassword.equals(password)) {
					return R.ok("新密码不能与原密码相同");
				}
				int i = userService.updatePassword(updatePasswordDTO);
				if (i > 0) {
					String generateToken = jwtTokenUtil.generateToken(username);
					redisUtil.pushToken(username, generateToken);
					return R.ok("更新密码成功");
				}
				return R.ok("更新密码失败");
			}
			return R.ok("原密码错误");
		}
		return R.error("token异常！");
		// String enPassword = userService.getPasswordByUsername(updatePasswordDTO.getUsername());
		// boolean isMatched = bCryptPasswordEncoderUtil.matches(updatePasswordDTO.getRawPassword(), enPassword);
		// if (isMatched) {
		// 	String password = updatePasswordDTO.getPassword();
		// 	String enNewPassword = bCryptPasswordEncoderUtil.encode(password);
		// 	updatePasswordDTO.setPassword(enNewPassword);
		// 	int i = userService.updatePassword(updatePasswordDTO);
		// 	if (i > 0) {
		// 		return R.ok("更新密码成功");
		// 	}
		// 	return R.ok("更新密码失败");
		// }
		// return R.ok("原密码错误");
		
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id删除用户")
	@DeleteMapping("/deleteUserByUserId")
	public R deleteUserByUserId(@PathParam("userId") @NotNull(message = "用户id不能为空") Integer userId) {
		boolean isDeleted = userService.removeById(userId);
		if (isDeleted) {
			return R.ok("删除成功");
		}
		return R.ok("删除失败");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id批量删除用户")
	@DeleteMapping("/deleteUsersByUserIds")
	public R deleteUsersByUserIds(@RequestBody List<Integer> idList) {
		boolean b = userService.removeByIds(idList);
		if (b) {
			return R.ok("批量删除用户成功");
		}
		return R.ok("批量删除用户失败");
	}
	
	@ApiOperation("根据用户名模糊查询用户")
	@GetMapping("/getUsersByUsernameWithPage")
	public R getUsersByUsernameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                             @RequestParam(defaultValue = "10") Integer pageSize,
	                                    String username) {
		Page<Map> page = new Page<Map>(currentPage, pageSize);
		IPage<UserWithRoleVO> users = userService.getUsersByUsernameWithPage(page, username);
		// HashMap<String, Object> map = new HashMap<>();
		// map.put("users", users);
		return R.ok("users", Collections.singletonList(users));
	}
}

