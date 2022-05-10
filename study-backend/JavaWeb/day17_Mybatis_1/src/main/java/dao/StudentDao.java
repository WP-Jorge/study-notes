package dao;

import domain.MyStudent;
import domain.Student;
import org.apache.ibatis.annotations.Param;
import vo.QueryParam;

import java.util.List;
import java.util.Map;

public interface StudentDao {
	// 查询student表的所有数据
	public List<Student> selectStudents();
	
	// 插入方法
	public int insertStudent(Student student);
	
	// 查询学生byID
	public Student selectStudentById(Integer id);
	
	/**
	 * 多个参数:命名参数，在形参定义的前面加入@Param("自定义参数名称”
	 */
	// 根据姓名或年龄查询学生
	public List<Student> selectStudentsByNameOrAge(@Param("myname") String name, @Param("myage") Integer age);
	
	/**
	 * 多个参数，使用java对象作为接口中的方法参数
	 */
	// 根据姓名或年龄查询学生
	public List<Student> selectStudentsByParamObject(QueryParam queryParam);
	
	/**
	 * 多个参数使用位置传值
	 */
	// 根据姓名或年龄查询学生
	public List<Student> selectStudentsByPosition(String name, Integer age);
	
	/**
	 * 多个参数使用Map存放多个值
	 */
	public List<Student> selectStudentsByMap(Map<Object, Object> map);
	
	// 定义返回Map
	public Map<Object, Object> selectMapById(Integer id);
	
	// 第一种resuleMap
	public List<MyStudent> selectAllStudents();
	
	// 第二种
	List<MyStudent> selectDiffcolProperty();
	
	// 第一种模糊查询，在java代码指定like的内容
	public List<Student> selectLikeOne(String name);
	
	// 第二种模糊查询，在mapper中拼接like "%" 李 "%"
	public List<Student> selectLikeTwo(String name);
}

