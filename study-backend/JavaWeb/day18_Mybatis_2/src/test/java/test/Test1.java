package test;

import dao.TeacherDao;
import domain.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DruidDataSourceFactory;
import utils.MyBatisUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test1 {
	private static final Logger log = LoggerFactory.getLogger(Test1.class);
	
	// 查询所有老师
	@Test
	public void test1() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
		List<Teacher> teacherList = teacherDao.getTeachers();
		System.out.println(teacherList);
		sqlSession.close();
	}
	
	// 查询所有老师以及学生1
	@Test
	public void test2() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
		Teacher teacher = teacherDao.getTeacher(1);
		System.out.println(teacher);
		sqlSession.close();
	}
	
	// 查询所有老师以及学生2
	@Test
	public void test3() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
		Teacher teacher = teacherDao.getTeacher2(1);
		System.out.println(teacher);
		sqlSession.close();
	}
	
	// 日志写入数据库
	@Test
	public void test4() {
		log.debug("This is debug");
		log.info("This is info");
		// log.warn("This is warn");
		// log.error("This is error");
		// log.error("This is error", new RuntimeException("this is a exception"));
	}
}
