package dao.impl;

import dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;

@Repository
public class LogDaoImpl implements LogDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertLog(String type, String op, String des, Timestamp time) {
		String sql = "insert into log_t values(null, ?, ?, ?, ?)";
		int i = jdbcTemplate.update(sql, type, op, des, time);
		return i;
	}
}
