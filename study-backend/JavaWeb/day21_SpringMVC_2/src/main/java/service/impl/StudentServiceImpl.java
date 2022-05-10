package service.impl;

import dao.StudentDao;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
	// 引用类型自动注入@Autowired
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public int addStudent(Student student) {
		int i = studentDao.insertStudent(student);
		// int x = 1 / 0;
		return i;
	}
	
	@Override
	public List<Student> findStudents() {
		return studentDao.selectStudents();
	}
}
