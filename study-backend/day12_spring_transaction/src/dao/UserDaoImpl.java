package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// public void reduceMoney() {
	// 	String sql = "update t_account set money = money - ? where username = ?";
	// 	jdbcTemplate.update(sql, 100, "lucy");
	//
	// }
	//
	// public void addMoney() {
	// 	String sql = "update t_account set money = money + ? where username = ?";
	// 	jdbcTemplate.update(sql, 100, "mary");
	// }
	
	public int reduceMoney(String name, Double money) {
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
		String sql = "update account_t set money = money - ? where name = ?";
		int i = jdbcTemplate.update(sql, money, name);
		return i;
	}
	
	public int addMoney(String name, Double money) {
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
		String sql = "update account_t set money = money + ? where name = ?";
		int i = jdbcTemplate.update(sql, money, name);
		return i;
	}
}
