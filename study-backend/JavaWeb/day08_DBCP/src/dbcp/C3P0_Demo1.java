package dbcp;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0_Demo1 {
	public static void main(String[] args) {
		// 1.创建数据库连接对象
		DataSource dataSource = new ComboPooledDataSource();
		try {
			// 2.获取连接对象
			Connection connection = dataSource.getConnection();
			// 3.打印连接对象（检查是否创建成功）
			System.out.println(connection);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}