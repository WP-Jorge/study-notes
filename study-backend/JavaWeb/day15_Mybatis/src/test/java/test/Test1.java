package test;

import domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import utils.MyBatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test1 {
	// select方法
	@Test
	public void test1() throws IOException {
		//访问mybatis读取student数据
		//1.定义mybatis主配置文件的名称，从类路径的根开始( target/ )
		String config = "mybatis.xml";
		//2.读取这个config表示的文件
		InputStream in = Resources.getResourceAsStream(config);
		//3.创建了SqlSessionFactoryBuilder对象
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//4.创建SqlSessionFactory对象
		SqlSessionFactory factory = builder.build(in);
		//5. [重要]获取SqlSession对象，从SqlSessionF actory中获取sqlSession
		SqlSession sqlSession = factory.openSession();
		//6. [重要]指定要执行的sql语句的标识。 sq1映射文件中的namespace + "." +标签的id值
		String sqlId = "dao.StudentDao" + "." + "selectStudents";
		//7. 执行sq1语句 ，通过sqlId找到语 句
		List<Student> studentlist = sqlSession.selectList(sqlId);
		//8.输 出结果
		studentlist.forEach(stu -> System.out.println(stu));
		//9.关闭sqlSession对象
		sqlSession.close();
	}
	
	// insert方法
	@Test
	public void test2() throws IOException {
		//访问mybatis读取student数据
		//1.定义mybatis主配置文件的名称，从类路径的根开始( target/ )
		String config = "mybatis.xml";
		//2.读取这个config表示的文件
		InputStream in = Resources.getResourceAsStream(config);
		//3.创建了SqlSessionFactoryBuilder对象
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//4.创建SqlSessionFactory对象
		SqlSessionFactory factory = builder.build(in);
		//5. [重要]获取SqlSession对象，从SqlSessionF actory中获取sqlSession
		SqlSession sqlSession = factory.openSession();
		//6. [重要]指定要执行的sql语句的标识。 sq1映射文件中的namespace + "." +标签的id值
		String sqlId = "dao.StudentDao" + "." + "insertStudnt";
		//7. 执行sq1语句 ，通过sqlId找到语 句
		Student student = new Student();
		student.setId(1003);
		student.setName("张飞");
		student.setEmail("zhangfei@qq.com");
		student.setAge(20);
		int count = sqlSession.insert(sqlId, student);
		// mybatis默认不提交事务，在insert、update、delete后需要手动提交事务
		sqlSession.commit();
		//8.输 出结果
		System.out.println("执行insert结果：" + count);
		//9.关闭sqlSession对象
		sqlSession.close();
	}
	
	// 测试MyBatisUtils
	@Test
	public void test3() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(false);
		String sqlId = "dao.StudentDao.selectStudents";
		List<Student> studentList = sqlSession.selectList(sqlId);
		System.out.println(studentList);
		sqlSession.close();
	}
}
