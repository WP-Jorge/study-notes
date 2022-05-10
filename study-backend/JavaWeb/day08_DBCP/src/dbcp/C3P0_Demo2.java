package dbcp;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;

/**
 * c3p0演示
 */
public class C3P0_Demo2 {
	public static void main(String[] args) throws SQLException {
		// 1.1 获取DataSource，使用默认配置
		// DataSource dataSource = new ComboPooledDataSource();// 什么也不传，使用默认配置，传入配置名则使用指定配置
		// 1.2 使用指定名称配置
		DataSource dataSource = new ComboPooledDataSource("myApp");// 使用myApp配置
		// 2.获取连接
		// 由于设置最大连接数为10，第十一个获取不到，报错
		for (int i = 1; i <= 11; i++) {
			Connection connection = dataSource.getConnection();
			System.out.println(i + ":" + connection);
			
			if (i == 5) {
				// connection.close(); // 归还连接到连接池中
			}
		}
	}
}
