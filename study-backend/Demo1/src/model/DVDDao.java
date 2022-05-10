package model;

import Util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DVDDao {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public List<DVDItem> getDVDList(String username) {
		String sql = "select title, year, genre from dvds, borrow, user where user.id = borrow.id and borrow.dvdid = dvds.dvdid and user.username = ?";
		List<DVDItem> dvdItemList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DVDItem>(DVDItem.class), username);
		
		return dvdItemList;
	}
}
