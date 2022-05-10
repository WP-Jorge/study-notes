package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * 使用自定义realm + md5 + salt + hash
 * 96e79218965eb72c92a549dd5a330112
 * d3e5f11743760ef6bf4f156fe61da585
 * f7302407bda4052e62339a2250f49238
 */
public class CustomerMd5Realm extends AuthorizingRealm {
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("=============================");
		// 1.获得主身分信息
		String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
		System.out.println("身份信息：" + primaryPrincipal);
		// 2.根据身份信息 用户名 获取当前用户角色信息以及权限信息 Jorge: admin,user
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 3.将数据库中查询角色信息赋值给权限对象
		// simpleAuthorizationInfo.addRole("admin");
		// simpleAuthorizationInfo.addRole("user");
		simpleAuthorizationInfo.addRoles(Arrays.asList("admin", "user"));
		// 4.将数据库中查询的权限信息赋值给某个权限对象
		simpleAuthorizationInfo.addStringPermission("user:*:01");
		simpleAuthorizationInfo.addStringPermission("product:create");
		return simpleAuthorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.获取身份信息
		String principal = (String) token.getPrincipal();
		// 2.根据用户名查询数据库
		if ("Jorge".equals(principal)) {
			return new SimpleAuthenticationInfo(
					// 用户名
					principal,
					// 加完盐的密码
					"f7302407bda4052e62339a2250f49238",
					// 盐
					ByteSource.Util.bytes("asd"),
					// realm名
					this.getName());
		}
		
		return null;
	}
}
