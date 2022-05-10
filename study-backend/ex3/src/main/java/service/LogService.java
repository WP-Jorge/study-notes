package service;

import dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;

@Service
public class LogService {
	@Autowired
	public LogDao logDao;
	public int insertIntoLog(String type, String op, String des, Timestamp time) {
		int i = logDao.insertLog(type, op, des, time);
		return i;
	}
}
