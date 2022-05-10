package com.example.oauth2_demo.security;

import com.example.oauth2_demo.entity.Permission;
import com.example.oauth2_demo.exception.MyAccessDeniedException;
import com.example.oauth2_demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class DynamicPermission {
	
	@Autowired
	PermissionService permissionService;
	
	// 判断有访问API的权限
	public Boolean checkPermission(HttpServletRequest request, Authentication authentication) throws MyAccessDeniedException {
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			// 得到当前用户名
			String username = userDetails.getUsername();
			//通过账号获取资源鉴权
			List<Permission> permissions = permissionService.getPermissionByUsername(username);
			AntPathMatcher antPathMatcher = new AntPathMatcher();
			// 当前访问路径
			String requestURI = request.getRequestURI();
			// 判断当前路径中是否在资源鉴权中
			boolean rs = permissions.stream().anyMatch(item -> {
				// 判断 url 是否匹配
				boolean hashAntPath = antPathMatcher.match(item.getPermission(), requestURI);
				return hashAntPath;
			});
			// 返回
			if (rs) {
				return rs;
			}
			throw new MyAccessDeniedException("您没有访问该api的权限！");
		}
		throw new MyAccessDeniedException("不是UserDetails类型！");
	}
}
