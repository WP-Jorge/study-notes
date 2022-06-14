package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.*;
import com.example.boxmusic.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
public interface UserService extends IService<User> {
	
	User getUserWithUsername(String username);
	
	UserVO getUserInfoWithUsername(String username);
	
	Boolean checkLogin(String username, String rawPassword);
	
	int addUser(User user);
	
	R logout(HttpServletRequest httpServletRequest);
	
	R adminRegister(MultipartFile picture, AddUserDTO addUserDTO);
	
	R getVerificationCode(String codeId);
	
	R verifyVerificationCode(String codeId, String verificationCode);
	
	R getUserInfo(String token);
	
	R getAllUsersPage(Page<Map<String, Object>> page);
	
	R getUsersByUsernamePage(Page<Map<String, Object>> page, String username);
	
	R deleteUsersByUserIds(List<Long> userIds);
	
	R updateUserStatus(Long userId, Integer status);
	
	R resetPasswordByUserId(Long userId);
	
	R updateUser(MultipartFile picture, UpdateUserAdminDTO updateUserAdminDTO);
	
	R addUser(MultipartFile picture, AddUserAdminDTO addUserAdminDTO);
	
	R updateUser(String headerToken, MultipartFile picture, UpdateUserDTO updateUserDTO);
	
	R updatePassword(String headerToken, PasswordDTO passwordDTO);
}
