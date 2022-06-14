package com.example.oauth2_demo.service.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MyUserDetails implements UserDetails {
	
	private String username;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	// private Boolean isWXLogin;
	
	// public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	// 	this.username = username;
	// 	this.password = password;
	// 	this.authorities = authorities;
	// }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
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