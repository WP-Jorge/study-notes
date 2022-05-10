package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// 在注解里面value可以省略，默认名称首字母小写
// @Component(value = "userService")// <bean id="userService" class=".."/>
// @Service(value = "userService")
// @Controller(value = "userService")
// @Repository(value = "userService")
@Service(value = "userService")
public class UserService {
	@Value(value = "abc")
	private String name;
	
	// 定义Dao类型的属性
	// 不需要添加set方法
	// 添加注入属性注解
	@Autowired // 根据类型进行注入
	@Qualifier(value = "userDaoImpl1") // 根据指定名称注入指定对象，需要跟Autowired一起使用
	private UserDao userDao;
	
	// @Resource // 根据类型进行输入
	// @Resource(name = "userDaoImpl1") // 根据名称进行输入，更建议我们使用Autowired或Qualifier
	// private UserDao userDao;
	public void add() {
		System.out.println("service add: " + name);
		userDao.add();
	}
}
