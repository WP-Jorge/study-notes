package com.example.boxmusic.service.auth;

import com.example.boxmusic.pojo.vo.ApiVO;
import com.example.boxmusic.pojo.vo.RoleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MyUserDetails implements UserDetails {
	
	private String username;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private List<RoleVO> roles;
	
	private List<ApiVO> apis;
	
	// private Boolean isWXLogin;
	
	// public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	// 	this.username = username;
	// 	this.password = password;
	// 	this.authorities = authorities;
	// }
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
