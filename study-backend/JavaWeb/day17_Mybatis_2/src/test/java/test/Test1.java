package test;

import com.github.pagehelper.PageHelper;
import com.mysql.cj.log.Log;
import dao.StudentDao;
import domain.Student;
import org.apache.ibatis.session.SqlSession;
// import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MyBatisUtils;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
	// static Logger logger = Logger.getLogger(Test1.class);
	private static final Logger log = LoggerFactory.getLogger(Test1.class);
	
	
	// 测试if
	@Test
	public void test1() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		Student student = new Student();
		student.setName("张三");
		student.setAge(20);
		List<Student> studentList = studentDao.selectStudentIf(student);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 测试where
	@Test
	public void test2() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		Student student = new Student();
		student.setName("张三");
		student.setAge(20);
		List<Student> studentList = studentDao.selectStudentWhere(student);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 测试foreeach1
	@Test
	public void test3() {
		List<Integer> list = new ArrayList<>();
		list.add(1001);
		list.add(1002);
		list.add(1003);
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		List<Student> studentList = studentDao.selectStudentEach(list);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 测试foreeach2
	@Test
	public void test4() {
		List<Student> stuList = new ArrayList<>();
		
		Student student1 = new Student();
		student1.setId(1001);
		Student student2 = new Student();
		student2.setId(1002);
		Student student3 = new Student();
		student3.setId(1003);
		
		stuList.add(student1);
		stuList.add(student2);
		stuList.add(student3);
		
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		List<Student> studentList = studentDao.selectStudentEachTwo(stuList);
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 测试分页
	@Test
	public void test5() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		
		// 加入PageHelper的方法，分页
		// pageNum: 第几页从1开始
		// pageSize：一页中有多少条数据
		PageHelper.startPage(1, 2);
		
		List<Student> studentList = studentDao.selectAll();
		System.out.println(studentList);
	}
	
	// 测试log4j
	@Test
	public void test6() {
		// logger.info("info：进入了log4j");
		// logger.debug("debug：进入了log4j");
		// logger.error("error: 进入了log4j");
	}
	
	// 测试log4j2
	@Test
	public void test7() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		
		// 加入PageHelper的方法，分页
		// pageNum: 第几页从1开始
		// pageSize：一页中有多少条数据
		PageHelper.startPage(1, 2);
		
		List<Student> studentList = studentDao.selectAll();
		System.out.println(studentList);
		log.warn("warn: 进入了log4j2");
		log.info("info：进入了log4j2");
		log.debug("debug：进入了log4j2");
		log.error("error: 进入了log4j2");
	}
	
	public static void main(String[] args) {
		log.warn("warn: 进入了log4j2");
		log.info("info：进入了log4j2");
		log.debug("debug：进入了log4j2");
		log.error("error: 进入了log4j2");
	}
}
