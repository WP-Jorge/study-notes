package dao;

import domain.Students;

import java.util.List;

public interface StudentsDao {
	
	// 查询所有学生信息1，以及对应老师的信息
	public List<Students> getStudent();
	
	// 查询所有学生信息2，以及对应老师的信息
	public List<Students> getStudent2();
}
