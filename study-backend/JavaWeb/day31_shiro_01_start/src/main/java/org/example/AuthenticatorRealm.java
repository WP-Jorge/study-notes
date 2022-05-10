package org.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.example.realm.CustomerRealm;

/**
 * 使用自定义realm
 */
public class AuthenticatorRealm {
	public static void main(String[] args) {
		// 1.创建securityManager
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		// 2.设置自定义realm
		securityManager.setRealm(new CustomerRealm());
		// 3.给安全工具类设置安全管理器
		SecurityUtils.setSecurityManager(securityManager);
		// 4.通过安全工具类获取subject
		Subject subject = SecurityUtils.getSubject();
		// 5.创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken("Jorge", "111111");
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名错误");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		}
	}
}
