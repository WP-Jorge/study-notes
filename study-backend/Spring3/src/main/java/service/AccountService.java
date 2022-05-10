package service;

import dao.AccountDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	public int transfer(String transferer, String beneficiary, Double transferAmount) {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		int i1 = accountDao.reduceMoney(transferer, transferAmount);
		// int a = 1 / 0;
		int i2 = accountDao.addMoney(transferer, transferAmount);
		if (i1 > 0 && i2 > 0) {
			return 1;
		}
		return 0;
	}
}
