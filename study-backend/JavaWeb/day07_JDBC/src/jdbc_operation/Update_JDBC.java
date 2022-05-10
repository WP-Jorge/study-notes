package jdbc_operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * tab1修改一条记录
 * tab1删除一条记录
 */
public class Update_JDBC {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			// 1.注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2.获取数据库连接对象
			connection = DriverManager.getConnection("jdbc:mysql:///dvd?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "111111");
			
			// 3.定义sql语句
			String sql = "update tab1 set count=count + 1 where id = 1";
			
			// 4.获取sql语句的对象
			statement = connection.createStatement();
			
			// 5.执行sql语句
			int count = statement.executeUpdate(sql);
			
			// 6.输出结果
			System.out.println(count);
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
		}
	}
}
