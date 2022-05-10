package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC
 * 概念：Java DataBase Connectivity Java 数据库连接，Java语言操作数据库
 * 本质：JDBC定义了一套操作所有关系型数据库的规则（接口），数据库产商去实现这套接口提供的数据库驱动jar包，我们可以使用这套接口（JDBC）编程
 * 真正执行的代码是驱动jar包中的实现类
 * 执行步骤：
 * 1.导入驱动jar包
 * 复制jar包到libs目录下
 * 将libs添加为库
 * 2.注册驱动
 * 3.获取数据库连接对象 Connection
 * 4.定义sql语句
 * 5.获取执行sql语句的对象Statement
 * 6.执行sql，接收返回的结果
 * 7.处理结果
 * 8.释放资源
 * 详解各个对象
 * 1.DriverManager：驱动管理对象
 * ① 注册驱动：告诉程序要使用哪一个数据库驱动jar
 * ② 获取数据库连接
 * 2.Connection：数据库连接对象
 * ① 获取sql对象
 * ② 管理事务
 * 开启事务：setAutoCommit(boolean autoCommit):调用该方法设置参数为false，即为开启事务
 * 提交事务：commit()
 * 回滚事务：rollback()
 * 使用：
 * 使用Connection对象来管理事务
 * 3.Statement：执行sql的对象
 * ① 执行sql
 * boolean execute(String sql)：可以执行任意的sql，了解
 * int executeUppdate(String sql)：执行DML(增删改insert、ipdate、delete)语句、DDL(create、alert、drop)语句（不常用）
 * 返回值：影响的行数，通过这个来判断DML语句是否执行成功，count > 0 ? success : fail
 * ResultSet executeQuery(String sql)：执行DQL(select)语句
 * ② 练习
 * tab1添加一条记录
 * tab1修改一条记录
 * tab1删除一条记录
 * <p>
 * 4.ResultSet：结果集对象,封装查询结果
 * ① boolean next()：游标向下移动一行
 * ② getXxx(参数)：获取数据
 * Xxx：代表数据类型，如 int getInt()
 * 参数：
 * int：代表列的编号，从一开始
 * String：代表列的名称，如 getInt("id")
 * ③ 使用步骤
 * 游标向下移动一行
 * 判断是否有数据
 * 获取数据
 * ④ 练习：定义一个方法，查询student表的数据将其封装为对象，然后装载集合
 * 定义Student类
 * 定义方法 public List<Student> findAll() {}
 * 实现方法select * from student;
 * 5.PrepareStatement：执行sql对象（更强大）
 * ① 防止sql注入，效率更高
 * ② 预编译的sql:参数使用？作为占位符
 * ③ 步骤
 * 1.导入驱动jar包
 * 复制jar包到libs目录下
 * 将libs添加为库
 * 2.注册驱动
 * 3.获取数据库连接对象 Connection
 * 4.定义sql语句
 * sql的参数使用占位符?代替，如 update tab1 set count = ? where id = ?;
 * 5.获取执行sql语句的对象ProparedStatement
 * Connection.propareStatement(String sql)
 * 6.个体sql赋值
 * 方法 setXxx(参数1, 参数2)
 * Xxx：设置参数类型
 * 参数1：参数的位置，从1开始
 * 参数2：?的值
 * 7.执行sql，接收返回的结果，不需要传递sql语句
 * 8.处理结果
 * 9.释放资源
 */
public class JDBC {
	public static void main(String[] args) throws Exception {
		// 1.导入驱动jar包
		
		// 2.注册驱动（可以不写，如果没写，默认"com.mysql.cj.jdbc.Driver"）
		// Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 3.获取数据库连接对象 Connection
		// jdbc:mysql://localhost:3306/dvd?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
		// jdbc:mysql://ip(域名):端口/数据库名?userUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
		// 如果IP为本地，端口为3306.则可以省略ip和端口
		// Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dvd?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "111111");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///dvd?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "111111");
		
		// 4.定义sql语句
		String sql = "update tab1 set count = 110 where id = 2";
		
		// 5.获取执行sql语句的对象Statement
		Statement statement = connection.createStatement();
		
		// 6.执行sql，接收返回的结果
		int count = statement.executeUpdate(sql);
		
		// 7.处理结果
		System.out.println(count);
		
		// 8.释放资源
		connection.close();
		statement.close();
	}
}
