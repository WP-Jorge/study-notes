package dao;

import domain.Teacher;
import org.apache.ibatis.annotations.Select;

public interface TeacherDao {
	@Select("select * from teacher where tid = #{tid}")
	Teacher getTeacher(Integer tid);
}
