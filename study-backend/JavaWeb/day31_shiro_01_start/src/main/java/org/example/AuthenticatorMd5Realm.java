package org.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.example.realm.CustomerMd5Realm;

import java.util.Arrays;

public class AuthenticatorMd5Realm {
	public static void main(String[] args) {
		// 1.创建securityManager
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		// 2.设置自定义realm
		CustomerMd5Realm realm = new CustomerMd5Realm();
		// 3.设置realm使用hash凭证匹配器
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 加密方式
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		// 散列次数
		hashedCredentialsMatcher.setHashIterations(1024);
		realm.setCredentialsMatcher(hashedCredentialsMatcher);
		securityManager.setRealm(realm);
		// 4.给安全工具类设置安全管理器
		SecurityUtils.setSecurityManager(securityManager);
		// 5.通过安全工具类获取subject
		Subject subject = SecurityUtils.getSubject();
		// 6.创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken("Jorge", "111111");
		try {
			subject.login(token);
			System.out.println("登陆成功");
		} catch (UnknownAccountException e) {
			System.out.println("用户名错误");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		}
		
		// 认证用户进行授权
		if (subject.isAuthenticated()) {
			
			// 1.基于角色的权限控制
			System.out.println("单权限验证：" + subject.hasRole("admin"));
			// 2.基于多角色权限控制
			System.out.println("多权限验证：" + subject.hasAllRoles(Arrays.asList("admin", "user")));
			// 3.是否具有其中一个角色
			boolean[] hasRoles = subject.hasRoles(Arrays.asList("admin", "user", "supper"));
			for (boolean hasRole : hasRoles) {
				System.out.println("具有其中一个角色：" + hasRole);
			}
			// 4.基于权限字符串的访问权限 资源标识符:操作:资源实例
			System.out.println("是否具有权限字符串的访问权限：" + subject.isPermitted("user:*:01"));
			System.out.println("是否具有权限字符串的访问权限：" + subject.isPermitted("product:create"));
			// 5.分别具有哪些权限
			boolean[] permitted = subject.isPermitted("user:*:01", "order:*:10");
			for (boolean b : permitted) {
				System.out.println("是否具有某个权限字符串的访问权限：" + b);
			}
			// 6.同时具有哪些权限
			boolean permittedAll = subject.isPermittedAll("user:*:01", "product:create:01");
			System.out.println("是否具有哪些权限：" + permittedAll);
		}
	}
}
