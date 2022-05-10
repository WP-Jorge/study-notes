package dao;

import domain.Student;

import java.util.List;

public interface StudentDao {
	// 动态sql，使用java对象作为参数
	List<Student> selectStudentIf(Student student);
	
	// 动态sql, where的使用，使用java对象作为参数
	List<Student> selectStudentWhere(Student student);
	
	// foreach 用法 1
	List<Student> selectStudentEach(List<Integer> list);
	
	// foreach 用法 2
	List<Student> selectStudentEachTwo(List<Student> studentList);
	
	// 使用PageHeaper分页
	List<Student> selectAll();
}

