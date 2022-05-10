package model;

import Util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public List<User> getUser(String username, String password) {
		String sql = "select * from user where username = ? and password = ?";
		// List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
		List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
		
		return userList;
	}
}
