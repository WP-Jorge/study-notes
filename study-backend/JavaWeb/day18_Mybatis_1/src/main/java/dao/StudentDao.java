package dao;

import domain.Student;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {
	// 如果数据库表头与实体类属性名不一致，使用@Results(id = "userMap", value = {@Result([id = true,] column = "数据库表头", property = "实体类属性名")})
	@Select("select * from student")
	@Results(id = "userMap", value = {
			@Result(id = true, column = "id", property = "sid"),
			@Result(column = "name", property = "sname"),
			@Result(column = "age", property = "sage"),
			@Result(column = "email", property = "semail"),
	})
	List<Student> getStudents();
	
	// 使用上面的userMap为map模板
	@Select("select * from student")
	@ResultMap("userMap")
	List<Student> getStudentss();
}

