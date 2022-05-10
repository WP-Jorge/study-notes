// package utils;
//
// import com.alibaba.druid.pool.DruidDataSourceFactory;
//
// import javax.sql.DataSource;
// import java.io.IOException;
// import java.io.InputStream;
// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.Properties;
//
// /**
//  * Druid连接池的工具类
//  */
// public class JDBCUtils {
// 	// 1.定义成员变量DataSource
// 	private static DataSource dataSource;
//
// 	static {
// 		try {
// 			// 2.加载配置文件
// 			Properties properties = new Properties();
// 			InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
// 			properties.load(resourceAsStream);
// 			// 3.获取DataSource
// 			dataSource = DruidDataSourceFactory.createDataSource(properties);
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
//
// 	/**
// 	 * 获取连接的方法
// 	 */
// 	public static Connection getConnection() throws SQLException {
// 		return dataSource.getConnection();
// 	}
//
// 	/**
// 	 * 释放资源
// 	 */
// 	public static void close(Statement statement, Connection connection) {
// 		/*if (statement != null) {
// 			try {
// 				statement.close();
// 			} catch (SQLException throwables) {
// 				throwables.printStackTrace();
// 			}
// 		}
// 		if (connection != null) {
// 			try {
// 				connection.close();// 归还连接
// 			} catch (SQLException throwables) {
// 				throwables.printStackTrace();
// 			}
// 		}*/
// 		close(null, statement,connection);
// 	}
//
// 	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
// 		if (resultSet != null) {
// 			try {
// 				resultSet.close();
// 			} catch (SQLException throwables) {
// 				throwables.printStackTrace();
// 			}
// 		}
// 		if (statement != null) {
// 			try {
// 				statement.close();
// 			} catch (SQLException throwables) {
// 				throwables.printStackTrace();
// 			}
// 		}
// 		if (connection != null) {
// 			try {
// 				connection.close();// 归还连接
// 			} catch (SQLException throwables) {
// 				throwables.printStackTrace();
// 			}
// 		}
// 	}
//
// 	/**
// 	 * 获取连接池的方法
// 	 */
// 	public static DataSource getDataSource() {
// 		return dataSource;
// 	}
// }
