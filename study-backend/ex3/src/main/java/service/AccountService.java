package service;

import dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = -1, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) // 可以加到类上方也可以加到方法上方
public class AccountService {
	@Autowired
	AccountDao accountDao;
	
	public int transfer(String transferer, String beneficiary, Double transferAmount) {
		int i1 = accountDao.reduceMoney(transferer, transferAmount);
		// int a = 1 / 0;
		int i2 = accountDao.addMoney(beneficiary, transferAmount);
		if (i1 > 0 && i2 > 0) {
			return 1;
		}
		return 0;
	}
}
