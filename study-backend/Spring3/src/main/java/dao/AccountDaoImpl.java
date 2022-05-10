package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import utils.MyBatisUtils;

@Component
public class AccountDaoImpl implements AccountDao {
	@Override
	public int reduceMoney(String name, Double money) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		int i1 = accountDao.reduceMoney(name, money);
		// sqlSession.commit();
		sqlSession.close();
		return i1;
	}
	
	@Override
	public int addMoney(String name, Double money) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
		int i2 = accountDao.addMoney(name, money);
		// sqlSession.commit();
		sqlSession.close();
		return i2;
	}
}
