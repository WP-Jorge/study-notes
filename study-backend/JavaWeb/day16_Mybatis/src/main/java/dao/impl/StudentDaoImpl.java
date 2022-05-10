package dao.impl;

import dao.StudentDao;
import domain.Student;
import org.apache.ibatis.session.SqlSession;
import utils.MyBatisUtils;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
	@Override
	public List<Student> selectStudents() {
		// 获取SqlSession对象
		SqlSession sqlSession = MyBatisUtils.getSqlSession();
		String config = "dao.StudentDao.selectStudents";
		// 执行sql语句。使用SqlSession类的方法
		List<Student> stuList = sqlSession.selectList(config);
		// 关闭
		sqlSession.close();
		return stuList;
	}
	
	@Override
	public int insertStudent(Student student) {
		SqlSession sqlSession = MyBatisUtils.getSqlSession(true);
		String config = "dao.StudentDao.insertStudent";
		int count = sqlSession.insert(config, student);
		sqlSession.close();
		return count;
	}
}
