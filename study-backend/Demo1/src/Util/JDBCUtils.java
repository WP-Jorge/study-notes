package Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
	// 定义成员变量DataSource
	private static DataSource dataSource = null;
	
	// 初始化静态代码块
	static {
		try {
			// 加载配置文件
			Properties properties = new Properties();
			InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
			properties.load(resourceAsStream);
			// 获取DataSource
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取连接方法
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	// 释放资源
	public static void close(Statement statement, Connection connection) {
		close(null, statement, connection);
	}
	
	private static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}
