package test;

import dao.impl.StudentDaoImpl;
import domain.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Test1 {
	
	/**
	 * List<student> studentList = dao. selectstudents(); 调用
	 * 1. dao对象，类型是studentDao，全限定名称是: com. bjpowernode . dao. studentDao
	 * 全限定名称和namespace是一样的。
	 * 2.方法名称，selectstudents，这个方法就是mapper文件中的id直selectstudents
	 * 3.通过dao中方法的返回值也可以确定MyBatis要调用的sqlSession的方法
	 * 如果返回值是List， 调用的是sqlSession. selectList()方法。
	 * 如果返回值int , 或是非List的，看mapper文件中的 标签是<insert> , <update>就会调用
	 * sqlSession的insert，update 等方法
	 */
	
	// 测试Impl中的selectStudents方法
	@Test
	public void test1() {
		StudentDaoImpl studentDao = new StudentDaoImpl();
		List<Student> studentList = studentDao.selectStudents();
		System.out.println(studentList);
	}
	
	// 测试Impl中的insertStudent方法
	@Test
	public void test2() {
		Student student = new Student();
		student.setId(1003);
		student.setName("张飞");
		student.setEmail("zhangsan@qq.com");
		student.setAge(20);
		StudentDaoImpl studentDao = new StudentDaoImpl();
		int count = studentDao.insertStudent(student);
		System.out.println("插入的结果：" + count);
	}
}
