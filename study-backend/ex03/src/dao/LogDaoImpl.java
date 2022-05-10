package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(timeout = -1, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) // 可以加到类上方也可以加到方法上方
public class LogDaoImpl implements LogDao {
	@Autowired
	AccountDaoImpl accountDao;
	public int transfer(String transferer, String beneficiary, Double money) {
		// AccountDaoImpl accountDao = new AccountDaoImpl();
		int i1 = accountDao.reduceMoney(transferer, money);
		int i2 = accountDao.addMoney(beneficiary, money);
		if (i1 > 0 && i2 > 0) {
			return 1;
		}
		return 0;
	}
}
