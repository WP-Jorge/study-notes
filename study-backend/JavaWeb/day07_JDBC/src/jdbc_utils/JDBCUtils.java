package jdbc_utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * 目的：简化书写
 * 分析：
 * 注册驱动对象
 * 抽取一个方法获取连接对象
 * 不传递参数，通过配置文件解决 jdbc.properties
 * 抽取一个方法来释放资源
 */
public class JDBCUtils {
	private static String url;
	private static String username;
	private static String password;
	private static String driver;
	
	/**
	 * 文件的读取，只执行一次
	 */
	static {
		// 读取资源文件，获取值
		try {
			// 1.创建Properties集合类
			Properties pro = new Properties();
			
			// 2.加载文件
			// 2.1获取src路径下的文件的方式---->ClassLoader 类加载器
			ClassLoader classLoader = JDBCUtils.class.getClassLoader();
			URL resource = classLoader.getResource("jdbc.properties");
			String path = resource.getPath();
			pro.load(new FileReader(path));
			
			// 3.获取属性赋值
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
			driver = pro.getProperty("driver");
			
			// 注册驱动
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 *
	 * @return 连接对象
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
	
	public static void close(Statement statement, Connection connection) {
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
	
	public static void close(Statement statement, Connection connection, ResultSet resultSet) {
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
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}

