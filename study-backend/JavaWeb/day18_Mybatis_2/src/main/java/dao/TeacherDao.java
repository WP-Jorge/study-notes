package dao;

import domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherDao {
	// 获取老师
	List<Teacher> getTeachers();
	
	// 获取指定老师下的所有学生及老师信息1
	Teacher getTeacher(@Param("tid") int tid);
	
	// 获取指定老师下的所有学生及老师信息2
	Teacher getTeacher2(@Param("tid") int tid);
}
