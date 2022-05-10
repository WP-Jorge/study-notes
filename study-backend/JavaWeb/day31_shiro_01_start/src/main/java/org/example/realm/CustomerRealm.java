package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm实现，将认证/授权数据的来源转为数据库的实现
 */
public class CustomerRealm extends AuthorizingRealm {
	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
	
	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.在token中获取用户名
		String principal = (String) token.getPrincipal();
		// String credentials = (String) token.getCredentials();
		// System.out.println(principal);
		// 2.根据身份信息去数据库中查找用户信息
		if (principal.equals("Jorge")) {
			// 参数一：返回数据库中正确的用户名
			// 参数二：返回数据库中正确的密码
			// 参数三：提供但钱realm的名字，this.getName()
			return new SimpleAuthenticationInfo(principal, "111111", this.getName());
		}
		
		return null;
	}
}
