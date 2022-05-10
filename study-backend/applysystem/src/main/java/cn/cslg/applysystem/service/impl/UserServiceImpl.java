package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.dto.AddUserDTO;
import cn.cslg.applysystem.pojo.dto.UpdatePasswordDTO;
import cn.cslg.applysystem.pojo.dto.UpdateUserDTO;
import cn.cslg.applysystem.pojo.entity.User;
import cn.cslg.applysystem.exception.MyAuthenticationException;
import cn.cslg.applysystem.exception.MyPasswordErrorException;
import cn.cslg.applysystem.mapper.UserMapper;
import cn.cslg.applysystem.service.UserService;
import cn.cslg.applysystem.utils.BCryptPasswordEncoderUtil;
import cn.cslg.applysystem.pojo.vo.UserWithRoleVO;
import cn.cslg.applysystem.pojo.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Autowired UserService userService;
	
	@Autowired
	BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
	
	@Override
	public User getUserByUsername(String username) {
		User user = baseMapper.getUserByUsername(username);
		return user;
	}
	
	@Override
	public Boolean checkLogin(String username, String rawPassword, Integer isAdmin) {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			// throw new UsernameNotFoundException("该用户不存在！");
			System.out.println(username + "用户不存在！");
			throw new MyPasswordErrorException("登录失败，用户名或密码错误！");
		}
		int adminFlag = user.getIsAdmin();
		if (isAdmin == adminFlag) {
			// 校验密码
			String password = user.getPassword();
			boolean matches = bCryptPasswordEncoderUtil.matches(rawPassword, password);
			if (matches) {
				System.out.println("登录成功");
				return true;
			}
			throw new MyPasswordErrorException("登录失败，用户名或密码错误！");
		}
		throw new MyAuthenticationException("抱歉，您当前暂时没有权限，请联系管理员！");
	}
	
	@Override
	public UserVO getUserInfoByUsername(String username) {
		
		return baseMapper.getUserInfoByUsername(username);
	}
	
	@Override
	public IPage<UserWithRoleVO> getAllUsersWithPage(Page<Map> page) {
		return baseMapper.getAllUsersWithPage(page);
	}
	
	@Override
	public List<UserVO> getAllUsers() {
		return baseMapper.getAllUsers();
	}
	
	@Override
	public int addUser(AddUserDTO addUserDTO) {
		String password = addUserDTO.getPassword();
		String encodePassword = bCryptPasswordEncoderUtil.encode(password);
		addUserDTO.setPassword(encodePassword);
		return baseMapper.addUser(addUserDTO);
	}
	
	@Override
	public int updateUser(UpdateUserDTO updateUserDTO) {
		return baseMapper.updateUser(updateUserDTO);
	}
	
	@Override
	public String getPasswordByUsername(String username) {
		return baseMapper.getPasswordByUsername(username);
	}
	
	@Override
	public int updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		return baseMapper.updatePassword(updatePasswordDTO);
	}
	
	@Override
	public IPage<UserWithRoleVO> getUsersByUsernameWithPage(Page<Map> page, String username) {
		return baseMapper.getUsersByUsernameWithPage(page, username);
	}
	
}
