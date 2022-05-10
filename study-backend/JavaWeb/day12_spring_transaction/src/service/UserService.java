package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = -1, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
// 可以加到类上方也可以加到方法上方
public class UserService {
	@Autowired
	private UserDao userDao;
	
	// 转账
	public void accountMoney() {
		// try {
		// 第一步：开启事务
		
		// 第二步：进行业务操作
		
		// lucy 少100
		userDao.reduceMoney();
		
		// 模拟异常
		// 运行后会发现lucy的钱少了，但是mary的钱却没加
		// int i = 1 / 0;
		
		// mary 多100
		userDao.addMoney();
		
		// 第三步：没有异常，提交事务
		// } catch (Exception e) {
		// 第四步：出现异常，事务回滚
		// }
		
		
	}
}
