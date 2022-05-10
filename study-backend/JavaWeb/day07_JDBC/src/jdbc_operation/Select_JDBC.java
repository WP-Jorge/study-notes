package jdbc_operation;

import com.mysql.cj.QueryResult;

import java.sql.*;

/**
 * select 查询
 */
public class Select_JDBC {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// 1.注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2.获取数据库连接对象
			connection = DriverManager.getConnection("jdbc:mysql:///dvd?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "111111");
			
			// 3.定义sql语句
			String sql = "select * from tab1";
			
			// 4.获取sql语句的对象
			statement = connection.createStatement();
			
			// 5.执行sql语句
			resultSet = statement.executeQuery(sql);
			
			// 6.输出结果
			// 循环判断结果集是否有下一行
			while (resultSet.next()) {
				// 获取数据
				int id = resultSet.getInt(1);
				int count = resultSet.getInt("count");
				System.out.println("id = " + id + "--count = " + count);
			}
			
			// 6.1.让光标向下移动一行
			// resultSet.next();
			// 6.2.获取数据库连接对象
			// int id = resultSet.getInt(1);
			// int count = resultSet.getInt("count");
			// System.out.println("id = " + id + "--count = " + count);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
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
}
