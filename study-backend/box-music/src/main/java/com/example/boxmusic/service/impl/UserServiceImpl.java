package com.example.boxmusic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.exception.MyPasswordErrorException;
import com.example.boxmusic.pojo.dto.AddUserAdminDTO;
import com.example.boxmusic.pojo.dto.AddUserDTO;
import com.example.boxmusic.pojo.dto.UpdateUserDTO;
import com.example.boxmusic.pojo.entity.User;
import com.example.boxmusic.mapper.UserMapper;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	VerificationCodeUtil verificationCodeUtil;
	
	@Autowired
	JsonUtil jsonUtil;
	
	@Override
	public User getUserWithUsername(String username) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		return baseMapper.selectOne(queryWrapper);
	}
	
	@Override
	public UserVO getUserInfoWithUsername(String username) {
		User user = this.getUserWithUsername(username);
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		return userVO;
	}
	
	@Override
	public Boolean checkLogin(String username, String rawPassword) {
		User user = this.getUserWithUsername(username);
		if (user == null) {
			// throw new UsernameNotFoundException("该用户不存在！");
			System.out.println(username + "用户不存在！");
			throw new MyPasswordErrorException("登录失败，用户名或密码错误！");
		}
		// 校验密码
		String password = user.getPassword();
		boolean matches = bCryptPasswordEncoderUtil.matches(rawPassword, password);
		if (matches) {
			System.out.println("登录成功");
			return true;
		}
		throw new MyPasswordErrorException("登录失败，用户名或密码错误！");
	}
	
	@Override
	public int addUser(AddUserDTO addUserDTO) {
		String password = addUserDTO.getPassword();
		String encodePassword = bCryptPasswordEncoderUtil.encode(password);
		addUserDTO.setPassword(encodePassword);
		User user = new User();
		BeanUtils.copyProperties(addUserDTO, user);
		return baseMapper.insert(user);
	}
	
	@Override
	public R logout(HttpServletRequest httpServletRequest) {
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		if (!("".equals(headerToken) || headerToken == null)) {
			// postMan 测试时，自动加上的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			String username = jwtTokenUtil.getUsernameFromToken(token);
			Boolean isSuccess = false;
			if (username != null) {
				isSuccess = redisUtil.deleteToken(username);
			}
			if (isSuccess) {
				return R.success("退出登录成功");
			}
			return R.error("退出登录失败，token 已失效，请重新登录");
		}
		return R.error("token 异常");
	}
	
	@Override
	public R register(MultipartFile file, AddUserDTO addUserDTO) {
		try {
			if (file != null) {
				String path = fileService.uploadImage(file);
				if (path == null) {
					return R.error("注册失败，图片上传失败");
				}
				addUserDTO.setUserPic(path);
			}
			int count = this.addUser(addUserDTO);
			if (count > 0) {
				return R.success("注册成功");
			}
			return R.error("注册失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("注册失败，用户名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R getVerifyCode(String codeId) {
		String verifyCodeBase64 = verificationCodeUtil.getVerificationCode(codeId);
		return R.success("获取验证码成功").put("verifyCodeBase64", verifyCodeBase64);
	}
	
	@Override
	public R verifyVerificationCode(String codeId, String verificationCode) {
		Boolean isTrue = verificationCodeUtil.verifyVerificationCode(codeId, verificationCode);
		if (isTrue) {
			return R.success("验证成功");
		}
		return R.error("验证失败");
	}
	
	@Override
	public R getUserInfo(HttpServletRequest httpServletRequest) {
		String headerToken = httpServletRequest.getHeader(Value.HEADER);
		HashMap<String, Object> map = new HashMap<>();
		
		if (!("".equals(headerToken) || headerToken == null)) {
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			
			String username = jwtTokenUtil.getUsernameFromToken(token);
			// List<RoleVO> roles = roleService.getRolesWithUsername(username);
			
			// List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			// for (RoleVO role : roles) {
			// 	authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			// }
			
			// userDetails.setUsername(username);
			// userDetails.setAuthorities(authorities);
			
			// 判断缓存中是否有 token 有则刷新 token，没有则生成
			// token = redisUtil.getToken(userDetails);
			// if (token != null) {
			// 	token = redisUtil.refreshToken(userDetails, token);
			// } else {
			// 	token = jwtTokenUtil.generateToken(userDetails);
			// 	redisUtil.pushToken(userDetails, token);
			// }
			
			// 将token放入map中
			UserVO userInfo = this.getUserInfoWithUsername(username);
			map.put("userInfo", userInfo);
			return R.success(map);
		}
		return R.error("token异常！");
	}
	
	@Override
	public R getAllUsersPage(Page<Map<String, Object>> page) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Page<Map<String, Object>> allUsersPage = baseMapper.selectMapsPage(page, queryWrapper);
		allUsersPage.setRecords(jsonUtil.transformPages(allUsersPage, UserVO.class));
		return R.successPage("获取用户成功", allUsersPage);
	}
	
	@Override
	public R getUsersByUsernamePage(Page<Map<String, Object>> page, String username) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		if (!"".equals(username) && username != null) {
			queryWrapper.like("username", username);
		}
		Page<Map<String, Object>> usersPage = baseMapper.selectMapsPage(page, queryWrapper);
		usersPage.setRecords(jsonUtil.transformPages(usersPage, UserVO.class));
		return R.successPage("获取用户成功", usersPage);
	}
	
	@Override
	public R deleteUsersByUserIds(List<Long> userIds) {
		int count = baseMapper.deleteBatchIds(userIds);
		if (count > 0) {
			return R.success("删除用户成功");
		}
		return R.error("删除用户失败");
	}
	
	@Override
	public R updateUserStatus(Long userId, Integer status) {
		User user = new User();
		user.setUserId(userId);
		user.setStatus(status);
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("user_id", user.getUserId())
				.set("status", user.getStatus());
		int i = baseMapper.update(user, updateWrapper);
		if (i > 0) {
			return R.success("更新用户状态成功");
		}
		return R.error("更新用户状态失败");
	}
	
	@Override
	public R resetPasswordByUserId(Long userId) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("user_id", userId)
				.set("password", bCryptPasswordEncoderUtil.encode("111111"));
		int i = baseMapper.update(null, updateWrapper);
		User user1 = baseMapper.selectById(userId);
		redisUtil.deleteByKey(user1.getUsername());
		if (i > 0) {
			return R.success("重置用户密码成功");
		}
		return R.error("重置用户密码失败");
	}
	
	@Override
	public R updateUser(MultipartFile file, UpdateUserDTO updateUserDTO) {
		try {
			UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
			if (file != null) {
				String path = fileService.uploadImage(file);
				if (path == null) {
					return R.error("更新失败，图片上传失败");
				}
				updateWrapper.set("user_pic", path);
			}
			User user = baseMapper.selectById(updateUserDTO.getUserId());
			if (user.getUserPic() != null && file == null) {
				Boolean deleteImage = fileService.deleteImage(user.getUserPic());
				if (!deleteImage) {
					return R.error("更新头像失败");
				}
				updateWrapper.set("user_pic", null);
			}
			updateWrapper.eq("user_id", updateUserDTO.getUserId())
					.set("username", updateUserDTO.getUsername())
					.set("sex", updateUserDTO.getSex())
					.set("age", updateUserDTO.getAge())
					.set("tel", updateUserDTO.getTel())
					.set("email", updateUserDTO.getEmail())
					.set("status", updateUserDTO.getStatus());
			int i = baseMapper.update(null, updateWrapper);
			if (i > 0) {
				return R.success("更新成功");
			}
			return R.error("更新失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，用户名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R addUser(MultipartFile file, AddUserAdminDTO addUserAdminDTO) {
		try {
			if (file != null) {
				String path = fileService.uploadImage(file);
				if (path == null) {
					return R.error("更新失败，图片上传失败");
				}
				addUserAdminDTO.setUserPic(path);
			}
			addUserAdminDTO.setPassword("111111");
			AddUserDTO addUserDTO = new AddUserDTO();
			BeanUtils.copyProperties(addUserAdminDTO, addUserDTO);
			int i = this.addUser(addUserDTO);
			if (i > 0) {
				return R.success("添加用户成功");
			}
			return R.error("添加失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，用户名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
