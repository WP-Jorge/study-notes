package dbcp;

import dao.Student;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
	① 修改12号学生name为王铁蛋
	② 添加一条数据
	③ 删除刚刚添加的数据
	④ 查询id为1的数据，将其封装为Map集合
	⑤ 查询所有记录，将其封装为List
	⑥ 查询所有记录，将其封装为Student的对象的List集合
	⑦ 查询总的记录数
*/
public class JdbcTemplate_Demo2 {
	// 1.创建JdbcTemplate对象
	JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
	
	// Junit单元测试
	// ① 修改12号学生name为王铁蛋
	@Test
	public void test1() {
		// 2.编写sql语句
		String sql = "update student set name = '王钢蛋' where id = ?";
		// 3.调用方法
		int count = jdbcTemplate.update(sql, 12);
		System.out.println(count);
	}
	
	// ② 添加一条数据
	@Test
	public void test2() {
		String sql = "insert into student values(null, ?)";
		int count = jdbcTemplate.update(sql, "少司命");
		System.out.println(count);
	}
	
	// ③ 删除刚刚添加的数据
	@Test
	public void test3() {
		String sql = "delete from student where name = ?";
		int count = jdbcTemplate.update(sql, "少司命");
		System.out.println(count);
		
	}
	
	// ④ 查询id为1的数据，将其封装为Map集合
	// 注意，queryForMap这个方法查询的结果集的长度只能为1
	@Test
	public void test4() {
		String sql = "select * from student where id = ?";
		Map<String, Object> objectMap = jdbcTemplate.queryForMap(sql, 1);
		System.out.println(objectMap);// {id=1, name=张三}
	}
	
	// ⑤ 查询所有记录，将其封装为List
	@Test
	public void test5() {
		String sql = "select * from student";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> stringObjectMap : list) {
			System.out.println(stringObjectMap);
		}
	}
	
	// ⑥ 查询所有记录，将其封装为Student的对象的List集合
	// 不使用自带的实现类，自己来实现
	@Test
	public void test6() {
		String sql = "select * from student";
		List<Student> studentList = jdbcTemplate.query(sql, new RowMapper<Student>() {
			
			@Override
			public Student mapRow(ResultSet resultSet, int i) throws SQLException {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Student student = new Student(id, name);
				
				return student;
			}
		});
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
	
	// 使用自带的实现类
	@Test
	public void test6_2() {
		String sql = "select * from student";
		List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
	
	// ⑦ 查询总的记录数
	@Test
	public void test7() {
		String sql = "select count(id) from student";
		Long aLong = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(aLong);
	}
}
