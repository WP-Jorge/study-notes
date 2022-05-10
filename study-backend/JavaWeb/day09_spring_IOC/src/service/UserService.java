package service;

import dao.UserDao;
import dao.UserDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserService {
	// 1、创建UserDao属性
	private UserDao userdao;
	
	// 2、生成set方法
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	
	public void add() {
		System.out.println("service add");
		userdao.update();
	}
}
