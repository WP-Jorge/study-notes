package service;

import dao.UserDao;
import dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = -1, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) // 可以加到类上方也可以加到方法上方
public class UserService {
	@Autowired
	private UserDao userDao;
	
	// 转账
	public int accountMoney(String transferer, String beneficiary, Double transferAmount) {
		// AccountDaoImpl accountDao = new AccountDaoImpl();
		int i1 = userDao.reduceMoney(transferer, transferAmount);
		// int a = 1 / 0;
		int i2 = userDao.addMoney(beneficiary, transferAmount);
		if (i1 > 0 && i2 > 0) {
			return 1;
		}
		return 0;
		
		
	}
}
