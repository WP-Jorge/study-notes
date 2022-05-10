package cn.cslg.applysystem.service.auth;

import cn.cslg.applysystem.pojo.entity.User;
import cn.cslg.applysystem.service.RoleService;
import cn.cslg.applysystem.service.UserService;
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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
		}
		//查找角色
		List<String> roles =  roleService.getRolesByUsername(username);
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		// 查找角色信息
		// roleService.
		return new MyUserDetails(user.getUsername(), user.getPassword(), authorities);
	}
}
