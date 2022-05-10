package dao;

import domain.Student;

import java.util.List;

public interface StudentDao {
	// 查询student表的所有数据
	public List<Student> selectStudents();
	
	// 插入方法
	public int insertStudent(Student student);
}

