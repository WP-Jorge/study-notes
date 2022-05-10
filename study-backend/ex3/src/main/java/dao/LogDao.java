package dao;

import java.sql.Date;
import java.sql.Timestamp;

public interface LogDao {
	public int insertLog(String type, String op, String des, Timestamp time);
}
