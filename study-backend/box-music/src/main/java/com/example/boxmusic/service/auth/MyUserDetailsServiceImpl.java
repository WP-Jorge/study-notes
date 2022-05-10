package com.example.boxmusic.service.auth;

import com.example.boxmusic.pojo.entity.User;
import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleVO;
import com.example.boxmusic.service.ApiService;
import com.example.boxmusic.service.RoleService;
import com.example.boxmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("myUserDetailsServiceImpl")
public class MyUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ApiService apiService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserWithUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
		}
		// 查找角色
		List<RoleVO> roles = roleService.getRolesWithUsername(username);
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (RoleVO role : roles) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
			authorities.add(simpleGrantedAuthority);
		}
		
		// 查询权限
		List<ApiVO> apis = apiService.getApisWithUsername(username);
		// 查找角色信息
		// roleService.
		return new MyUserDetails(user.getUsername(), user.getPassword(), authorities, roles, apis);
	}
}
