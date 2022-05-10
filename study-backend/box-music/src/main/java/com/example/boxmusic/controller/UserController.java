package com.example.boxmusic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddUserAdminDTO;
import com.example.boxmusic.pojo.dto.AddUserDTO;
import com.example.boxmusic.pojo.dto.UpdateUserDTO;
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
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	MyUserDetails userDetails;
	
	@Autowired
	RoleService roleService;
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("用户注册")
	@PostMapping("/register")
	public R register(MultipartFile file, @Valid AddUserDTO addUserDTO) {
		return userService.register(file, addUserDTO);
	}
	
	@ApiOperation("退出登录")
	@PostMapping("/logout")
	public R logout(HttpServletRequest httpServletRequest) {
		return userService.logout(httpServletRequest);
	}
	
	@ApiOperation("根据token获取用户信息")
	@GetMapping("/getUserInfo")
	public R getUserInfo(HttpServletRequest httpServletRequest) {
		return userService.getUserInfo(httpServletRequest);
	}
	
	@ApiOperation("获取验证码图片")
	@GetMapping("/getVerifyCode")
	public R getVerifyCode(@NotBlank(message = "验证码id不能为空") @RequestParam String codeId) {
		return userService.getVerifyCode((codeId));
	}
	
	@ApiOperation("校验验证码")
	@GetMapping("/verifyVerificationCode")
	public R verifyVerificationCode(@NotBlank(message = "验证码id不能为空") @RequestParam String codeId, @NotBlank(message = "验证码不能为空") @RequestParam String verificationCode) {
		return userService.verifyVerificationCode(codeId, verificationCode);
	}
	
	@ApiOperation(("获取用户列表"))
	@GetMapping("/getAllUsersPage")
	public R getAllUsersPage(@RequestParam(defaultValue = "1") Integer currentPage,
							 @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return userService.getAllUsersPage(page);
	}
	
	@ApiOperation("根据用户名模糊查询用户")
	@GetMapping("/getUsersByUsernamePage")
	public R getUsersByUsernamePage(@RequestParam(defaultValue = "1") Integer currentPage,
									@RequestParam(defaultValue = "10") Integer pageSize,
									@RequestParam(defaultValue = "") String username) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return userService.getUsersByUsernamePage(page, username);
	}
	
	@ApiOperation("根据用户id批量删除用户")
	@DeleteMapping("/deleteUsersByUserIds")
	public R deleteUsersByUserIds(@RequestBody @NotEmpty(message = "用户id不能为空") List<Long> userIds) {
		return userService.deleteUsersByUserIds(userIds);
	}
	
	@ApiOperation("根据用户id更新用户状态")
	@PatchMapping("/updateUserStatus/{userId}/{status}")
	public R updateUserStatus(@PathVariable("userId") @NotNull(message = "用户id不能为空") Long userId, @PathVariable("status") @NotNull(message = "用户状态不能为空") Integer status) {
		return userService.updateUserStatus(userId, status);
	}
	
	@ApiOperation("根据用户id重置用户密码")
	@PatchMapping("/resetPasswordByUserId/{userId}")
	public R resetPasswordByUserId(@PathVariable("userId") @NotNull(message = "用户id不能为空") Long userId) {
		return userService.resetPasswordByUserId(userId);
	}
	
	@ApiOperation("更新用户")
	@PutMapping("/updateUser")
	public R updateUser(MultipartFile file, @Valid UpdateUserDTO updateUserDTO) {
		return userService.updateUser(file, updateUserDTO);
	}
	
	@ApiOperation("添加用户")
	@PostMapping("/addUser")
	public R addUser(MultipartFile file, @Valid AddUserAdminDTO addUserAdminDTO) {
		return userService.addUser(file, addUserAdminDTO);
	}
}

