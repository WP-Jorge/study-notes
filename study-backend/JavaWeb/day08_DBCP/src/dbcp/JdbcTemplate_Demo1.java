package dbcp;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

/**
 * JdbcTemplate基本入门
 */
public class JdbcTemplate_Demo1 {
	public static void main(String[] args) {
		// 1.导入jar包
		// 2.创建JdbcTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
		// 编写sql语句
		String sql = "update student set name = '大铁蛋' where id = ?";
		// 4.调用方法
		int count = jdbcTemplate.update(sql, 12);
		System.out.println(count);
	}
}
