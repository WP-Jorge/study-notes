package test;

import dao.StudentDao;
import domain.MyStudent;
import domain.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import utils.MyBatisUtils;
import vo.QueryParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
	// 测试selectStudents方法
	@Test
	public void test1() {
		/**
		 * 使用mybatis的动态代理机制
		 *      1、使用SqlSession.getMapper(dao接口)
		 *          getMapper能获取dao接口对子的实现类对象。
		 */
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		
		// System.out.println("dao = " + studentDao.getClass().getName());
		// 调用dao方法，执行数据库操作
		List<Student> studentList = studentDao.selectStudents();
		System.out.println(studentDao);
	}
	
	// 测试insertStudent方法
	@Test
	public void test2() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		Student student = new Student();
		student.setAge(10);
		student.setEmail("aqi@qq.com");
		student.setName("阿七");
		student.setId(1004);
		int i = studentDao.insertStudent(student);
		System.out.println("插入状态：" + i);
		sqlSession.close();
	}
	
	// 根据id查询学生
	@Test
	public void test3() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		Student student = studentDao.selectStudentById(1003);
		System.out.println(student);
		sqlSession.close();
	}
	
	// 测试@Param命名，根据学生姓名或年龄查询学生
	@Test
	public void test4() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		List<Student> studentList = studentDao.selectStudentsByNameOrAge("张三", 20);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 测试Param对象内部属性传参
	@Test
	public void test5() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		QueryParam queryParam = new QueryParam("张三", 20);
		List<Student> studentList = studentDao.selectStudentsByParamObject(queryParam);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	/**
	 * 多个参数-简单类型的,按位置传值，
	 * mybatis.3.4之前,使用#{0} , #{1}
	 * mybatis。3.4之后,使用#{arg0}， #{arg1}
	 */
	// 多个参数使用位置传值
	@Test
	public void test6() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		List<Student> studentList = studentDao.selectStudentsByPosition("李四", 20);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	/**
	 * map传参
	 */
	// 多个参数使用map传值
	@Test
	public void test7() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		Map<Object, Object> data = new HashMap<>();
		data.put("myname", "张三");
		data.put("myage", 20);
		List<Student> studentList = studentDao.selectStudentsByMap(data);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 返回Map
	@Test
	public void test8() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		Map<Object, Object> map = studentDao.selectMapById(1003);
		System.out.println(map);
		sqlSession.close();
	}
	
	// resultMap
	@Test
	public void test9() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		List<MyStudent> studentList = studentDao.selectDiffcolProperty();
		System.out.println(studentList);
		for (MyStudent student : studentList) {
			System.out.println(student);
		}
	}
	
	// likeOne
	@Test
	public void test10() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		// 准备好like的内容
		String name = "%李%";
		
		List<Student> studentList = studentDao.selectLikeOne(name);
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
	
	// likeTwo
	@Test
	public void test11() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		List<Student> studentList = studentDao.selectLikeTwo("张");
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
