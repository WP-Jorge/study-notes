package Util;

import model.DVDItem;
import model.User;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Junit {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
	
	@Test
	public void test01() {
		String sql = "select * from `user` where username = ? and password = ?";
		List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), "zhangsan", "111111");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void test02() {
		String sql = "select title, `year`, genre from dvds, borrow, `user` where `user`.id = borrow.id and borrow.dvdid = dvds.dvdid and `user`.username = ?";
		List<DVDItem> dvdItemList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DVDItem>(DVDItem.class), "zhangsan");
		for (DVDItem dvdItem : dvdItemList) {
			System.out.println(dvdItem);
		}
	}
}
