package com.example.shiro_springboot.shiro.realms;

import com.example.shiro_springboot.entity.Perms;
import com.example.shiro_springboot.entity.User;
import com.example.shiro_springboot.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		// 获取身份信息
		String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
		System.out.println("调用授权验证：" + primaryPrincipal);
		// 根据主身份信息获取角色信息和权限信息
		User user = userService.findRolesByUsername(primaryPrincipal);
		// User user = new User();
		// System.out.println(111);
		System.out.println(user.getRoles());
		// 授权角色
		if (!CollectionUtils.isEmpty(user.getRoles())) {
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			user.getRoles().forEach(role -> {
				System.out.println(role.getRoleName());
				simpleAuthorizationInfo.addRole(role.getRoleName());
				
				// 权限信息
				List<Perms> permsList = userService.findPermsByRoleId(role.getRoleId());
				if (!CollectionUtils.isEmpty(permsList)) {
					permsList.forEach(perms -> {
						System.out.println(perms.getPermsName());
						simpleAuthorizationInfo.addStringPermission(perms.getPermsName());
					});
				}
			});
			return simpleAuthorizationInfo;
		}
		
		
		// if ("野猪乔治".equals(primaryPrincipal)) {
		// 	SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 	// 添加权限
		// 	simpleAuthorizationInfo.addRole("user");
		// 	simpleAuthorizationInfo.addRole("admin");
		// 	// 权限字符串
		// 	// simpleAuthorizationInfo.addStringPermission("admin:*:*");
		// 	return simpleAuthorizationInfo;
		// }
		
		
		return null;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String principal = (String) token.getPrincipal();
		Object credentials = token.getCredentials();
		System.out.println(credentials.toString());
		// 在工厂中获取service对象
		// UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
		
		// 根据用户名获取用户对象
		User user = userService.findByUsername(principal);
		
		if (!ObjectUtils.isEmpty(user)) {
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
		}
		return null;
	}
}
