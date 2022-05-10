package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddUserAdminDTO;
import com.example.boxmusic.pojo.dto.AddUserDTO;
import com.example.boxmusic.pojo.dto.UpdateUserDTO;
import com.example.boxmusic.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.utils.R;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
	
	int addUser(AddUserDTO addUserDTO);
	
	R logout(HttpServletRequest httpServletRequest);
	
	R register(MultipartFile file, AddUserDTO addUserDTO);
	
	R getVerifyCode(String codeId);
	
	R verifyVerificationCode(String codeId, String verificationCode);
	
	R getUserInfo(HttpServletRequest httpServletRequest);
	
	R getAllUsersPage(Page<Map<String, Object>> page);
	
	R getUsersByUsernamePage(Page<Map<String, Object>> page, String username);
	
	R deleteUsersByUserIds(List<Long> userIds);
	
	R updateUserStatus(Long userId, Integer status);
	
	R resetPasswordByUserId(Long userId);
	
	R updateUser(MultipartFile file, UpdateUserDTO updateUserDTO);
	
	R addUser(MultipartFile file, AddUserAdminDTO addUserAdminDTO);
}
