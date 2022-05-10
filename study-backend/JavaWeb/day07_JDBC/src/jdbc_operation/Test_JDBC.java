package jdbc_operation;

import dao.Student;
import jdbc_utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test_JDBC {
	public static void main(String[] args) {
		List<Student> list = new Test_JDBC().findAll3("1");
		System.out.println(list);
	}
	
	/**
	 * 查询所有Student对象
	 *
	 * @return
	 */
	public List<Student> findAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			// 1.注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2.获取数据库连接对象
			connection = DriverManager.getConnection("jdbc:mysql:///dvd?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "111111");
			
			// 3.定义sql语句
			String sql = "select * from student";
			
			// 4.获取sql语句的对象
			statement = connection.createStatement();
			
			// 5.执行sql语句
			resultSet = statement.executeQuery(sql);
			
			// 6.输出结果
			// 循环判断结果集是否有下一行
			Student student = null;
			while (resultSet.next()) {
				// 获取数据
				int id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				student = new Student(id, name);
				list.add(student);
				// System.out.println("id = " + id + "--count = " + name);
			}
			
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
		return list;
	}
	
	public List<Student> findAll2() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JDBCUtils.getConnection();
			// 3.定义sql语句
			String sql = "select * from student";
			
			// 4.获取sql语句的对象
			statement = connection.createStatement();
			
			// 5.执行sql语句
			resultSet = statement.executeQuery(sql);
			
			// 6.输出结果
			// 循环判断结果集是否有下一行
			Student student = null;
			while (resultSet.next()) {
				// 获取数据
				int id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				student = new Student(id, name);
				list.add(student);
				// System.out.println("id = " + id + "--count = " + name);
			}
			
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			JDBCUtils.close(statement, connection, resultSet);
		}
		return list;
	}
	
	public List<Student> findAll3(String ID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JDBCUtils.getConnection();
			// 3.定义sql语句
			String sql = "select * from student where id = ?";
			
			// 4.获取sql语句的对象
			preparedStatement = connection.prepareStatement(sql);
			
			// 给?赋值
			preparedStatement.setString(1, ID);
			
			// 5.执行sql语句,不需要传参
			resultSet = preparedStatement.executeQuery();
			
			// 6.输出结果
			// 循环判断结果集是否有下一行
			Student student = null;
			while (resultSet.next()) {
				// 获取数据
				int id = resultSet.getInt(1);
				String name = resultSet.getString("name");
				student = new Student(id, name);
				list.add(student);
				// System.out.println("id = " + id + "--count = " + name);
			}
			
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			JDBCUtils.close(preparedStatement, connection, resultSet);
		}
		return list;
	}
}
