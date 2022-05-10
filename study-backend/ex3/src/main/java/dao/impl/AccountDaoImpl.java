package dao.impl;

import dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int reduceMoney(String name, Double money) {
		String sql = "update account_t set money = money - ? where name = ?";
		int i = jdbcTemplate.update(sql, money, name);
		return i;
	}
	
	@Override
	public int addMoney(String name, Double money) {
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
		String sql = "update account_t set money = money + ? where name = ?";
		int i = jdbcTemplate.update(sql, money, name);
		return i;
	}
}
