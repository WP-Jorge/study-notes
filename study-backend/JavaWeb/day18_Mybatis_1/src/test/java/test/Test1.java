package test;

import dao.StudentDao;
import dao.StudentsDao;
import dao.TeacherDao;
import domain.Student;
import domain.Students;
import domain.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import utils.MyBatisUtils;

import java.util.List;

public class Test1 {
	// 测试注解开发
	@Test
	public void test1() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
		// List<Student> studentList = studentDao.getStudents();
		List<Student> studentList = studentDao.getStudentss();
		System.out.println(studentList);
		sqlSession.close();
	}
	
	// 测试根据老师id插老师
	@Test
	public void test2() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
		Teacher teacher = teacherDao.getTeacher(1);
		System.out.println(teacher);
		sqlSession.close();
	}
	
	// 查询所有学生以及对应教师1
	@Test
	public void test3() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		StudentsDao studentsDao = sqlSession.getMapper(StudentsDao.class);
		List<Students> studentsList = studentsDao.getStudent();
		System.out.println(studentsList);
		sqlSession.close();
	}
	
	// 查询所有学生以及对应教师2
	@Test
	public void test4() {
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		StudentsDao studentsDao = sqlSession.getMapper(StudentsDao.class);
		List<Students> studentsList = studentsDao.getStudent2();
		System.out.println(studentsList);
		sqlSession.close();
	}
}
