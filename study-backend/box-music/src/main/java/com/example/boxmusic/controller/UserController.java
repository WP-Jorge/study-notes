package com.example.boxmusic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.*;
import com.example.boxmusic.service.RoleService;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.service.auth.MyUserDetails;
import com.example.boxmusic.utils.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@RestController
// @RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MyUserDetails userDetails;
	
	@Autowired
	private RoleService roleService;
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("用户注册")
	@PostMapping("/user/adminRegister")
	public R adminRegister(MultipartFile picture, @Valid AddUserDTO addUserDTO) {
		return userService.adminRegister(picture, addUserDTO);
	}
	
	@ApiOperation("退出登录")
	@PostMapping("/user/logout")
	public R logout(HttpServletRequest httpServletRequest) {
		return userService.logout(httpServletRequest);
	}
	
	@ApiOperation("根据token获取用户信息")
	@GetMapping("/user/getUserInfo")
	public R getUserInfo(HttpServletRequest httpServletRequest) {
		return userService.getUserInfo(httpServletRequest.getHeader(Value.HEADER));
	}
	
	@ApiOperation("获取验证码图片")
	@GetMapping("/user/getVerificationCode")
	public R getVerificationCode(@NotNull(message = "验证码id不能为空") @RequestParam String codeId) {
		return userService.getVerificationCode((codeId));
	}
	
	@ApiOperation("校验验证码")
	@GetMapping("/user/verifyVerificationCode")
	public R verifyVerificationCode(@NotNull(message = "验证码id不能为空") @RequestParam String codeId, @NotBlank(message = "验证码不能为空") @RequestParam String verificationCode) {
		return userService.verifyVerificationCode(codeId, verificationCode);
	}
	
	@ApiOperation(("获取用户列表"))
	@GetMapping("/admin/getAllUsersPage")
	public R getAllUsersPage(@RequestParam(defaultValue = "1") Integer currentPage,
							 @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return userService.getAllUsersPage(page);
	}
	
	@ApiOperation("根据用户名模糊查询用户")
	@GetMapping("/admin/getUsersByUsernamePage")
	public R getUsersByUsernamePage(@RequestParam(defaultValue = "1") Integer currentPage,
									@RequestParam(defaultValue = "10") Integer pageSize,
									@RequestParam(defaultValue = "") String username) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return userService.getUsersByUsernamePage(page, username);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id批量删除用户")
	@DeleteMapping("/admin/deleteUsersByUserIds")
	public R deleteUsersByUserIds(@RequestBody @NotEmpty(message = "用户id不能为空") List<Long> userIds) {
		return userService.deleteUsersByUserIds(userIds);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id更新用户状态")
	@PatchMapping("/admin/updateUserStatus/{userId}/{status}")
	public R updateUserStatus(@PathVariable("userId") @NotNull(message = "用户id不能为空") Long userId, @PathVariable("status") @NotNull(message = "用户状态不能为空") Integer status) {
		return userService.updateUserStatus(userId, status);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据用户id重置用户密码")
	@PatchMapping("/admin/resetPasswordByUserId/{userId}")
	public R resetPasswordByUserId(@PathVariable("userId") @NotNull(message = "用户id不能为空") Long userId) {
		return userService.resetPasswordByUserId(userId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新用户")
	@PutMapping("/admin/updateUser")
	public R updateUser(MultipartFile picture, @Valid UpdateUserAdminDTO updateUserAdminDTO) {
		return userService.updateUser(picture, updateUserAdminDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加用户")
	@PostMapping("/admin/addUser")
	public R addUser(MultipartFile picture, @Valid AddUserAdminDTO addUserAdminDTO) {
		return userService.addUser(picture, addUserAdminDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据token更新用户")
	@PutMapping("/user/updateUser")
	public R updateUser(HttpServletRequest httpServletRequest, MultipartFile picture, @Valid UpdateUserDTO updateUserDTO) {
		return userService.updateUser(httpServletRequest.getHeader(Value.HEADER), picture, updateUserDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据token更新用户密码")
	@PutMapping("/user/updatePassword")
	public R updatePassword(HttpServletRequest httpServletRequest, @Valid @RequestBody PasswordDTO passwordDTO) {
		return userService.updatePassword(httpServletRequest.getHeader(Value.HEADER), passwordDTO);
	}
}

