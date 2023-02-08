package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.exception.MyPasswordErrorException;
import com.example.boxmusic.pojo.dto.*;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.pojo.entity.Singer;
import com.example.boxmusic.pojo.entity.User;
import com.example.boxmusic.mapper.UserMapper;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.service.UserRoleService;
import com.example.boxmusic.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.*;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
	private UserRoleService userRoleService;
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	RedisUtil redisUtil;
	
	@Autowired
	VerificationCodeUtil verificationCodeUtil;
	
	@Autowired
	JsonUtil jsonUtil;

	@org.springframework.beans.factory.annotation.Value("${basePath}")
	private String basePath;
	
	@org.springframework.beans.factory.annotation.Value("${userPicturePath}")
	private String userPicturePath;
	
	@Autowired
	private CachedUidGenerator cachedUidGenerator;
	
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
	public int addUser(User user) {
		String password = user.getPassword();
		String encodePassword = bCryptPasswordEncoderUtil.encode(password);
		user.setPassword(encodePassword);
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
	public R adminRegister(MultipartFile picture, AddUserDTO addUserDTO) {
		try {
			if (picture == null) {
				return R.error("注册失败，请上传头像");
			}
			
			long uid = cachedUidGenerator.getUID();
			String pictureName = fileService.getFilename(picture, uid);
			User user = new User();
			BeanUtils.copyProperties(addUserDTO, user);
			user.setUserId(uid);
			user.setUserPic(pictureName);
			int i = this.addUser(user);
			if (i <= 0) {
				throw new RuntimeException("注册失败");
			}
			ArrayList<Long> roles = new ArrayList<>();
			roles.add(1533595461280165889l);
			Boolean aBoolean = userRoleService.addUserRoles(uid, roles);
			if (!aBoolean) {
				throw new RuntimeException("注册失败");
			}
			fileService.uploadFile(picture, basePath + userPicturePath, pictureName);
			return R.success("注册成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("注册失败，用户名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R getVerificationCode(String codeId) {
		String VerificationCodeBase64 = verificationCodeUtil.getVerificationCode(codeId);
		return R.success("获取验证码成功").put("VerificationCodeBase64", VerificationCodeBase64);
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
	public R getUserInfo(String headerToken) {
		HashMap<String, Object> map = new HashMap<>();
		
		if (!("".equals(headerToken) || headerToken == null)) {
			// postMan测试时，自动假如的前缀，要去掉。
			String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
			String username = jwtTokenUtil.getUsernameFromToken(token);
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
	public R updateUser(MultipartFile picture, UpdateUserAdminDTO updateUserAdminDTO) {
		try {
			User user = baseMapper.selectById(updateUserAdminDTO.getUserId());
			BeanUtils.copyProperties(updateUserAdminDTO, user);
			String pictrueName = user.getUserPic();
			String picturefilename = null;
			if (picture != null) {
				picturefilename = fileService.getFilename(picture, user.getUserId());
				user.setUserPic(picturefilename);
			}
			int i = baseMapper.updateById(user);
			if (i <= 0) {
				throw new RuntimeException("更新失败");
			}
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(basePath + userPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, basePath + userPicturePath, picturefilename);
			}
			return R.success("更新成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，用户名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R addUser(MultipartFile picture, AddUserAdminDTO addUserAdminDTO) {
		try {
			if (picture == null) {
				return R.error("添加失败，请上传头像");
			}
			long uid = cachedUidGenerator.getUID();
			String pictureName = fileService.getFilename(picture, uid);
			User user = new User();
			BeanUtils.copyProperties(addUserAdminDTO, user);
			user.setUserId(uid);
			user.setUserPic(pictureName);
			user.setPassword("111111");
			int i = this.addUser(user);
			if (i <= 0) {
				throw new RuntimeException("添加失败");
			}
			ArrayList<Long> roles = new ArrayList<>();
			roles.add(1533595461280165889l);
			Boolean aBoolean = userRoleService.addUserRoles(uid, roles);
			if (!aBoolean) {
				throw new RuntimeException("添加失败");
			}
			fileService.uploadFile(picture, basePath + userPicturePath, pictureName);
			return R.success("添加成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，用户名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R updateUser(String headerToken, MultipartFile picture, UpdateUserDTO updateUserDTO) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		// postMan测试时，自动假如的前缀，要去掉。
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		try {
			QueryWrapper<User> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("username", username);
			User user = baseMapper.selectOne(queryWrapper);
			BeanUtils.copyProperties(updateUserDTO, user);
			String pictrueName = user.getUserPic();
			String picturefilename = null;
			if (picture != null) {
				picturefilename = fileService.getFilename(picture, user.getUserId());
				user.setUserPic(picturefilename);
			}
			int i = baseMapper.updateById(user);
			if (i <= 0) {
				throw new RuntimeException("更新失败");
			}
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(basePath + userPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, basePath + userPicturePath, picturefilename);
			}
			return R.success("更新成功");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R updatePassword(String headerToken, PasswordDTO passwordDTO) {
		if ("".equals(headerToken) || headerToken == null) {
			return R.error("token异常");
		}
		String token = headerToken.replace(Value.POSTMAN_TOKEN_PREFIX, Value.ENTITY).trim();
		String username = jwtTokenUtil.getUsernameFromToken(token);
		try {
			UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("username", username)
					.set("password", bCryptPasswordEncoderUtil.encode(passwordDTO.getPassword()));
			int i = baseMapper.update(null, updateWrapper);
			if (i > 0) {
				return R.success("更新密码成功");
			}
			return R.error("更新密码失败");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
