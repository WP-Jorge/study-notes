package dao;

import domain.Student;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
	
	int insertStudent(Student student);
	
	// @Select("select * from student")
	List<Student> selectStudents();
}
