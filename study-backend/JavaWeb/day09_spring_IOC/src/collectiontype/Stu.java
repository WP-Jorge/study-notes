package collectiontype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stu {
	// 1、数组类型的属性
	private String[] course;
	
	// 2、List集合类型的属性
	private List<String> list;
	
	// 3、Map集合类型的属性
	private Map<String, Object> map;
	
	// 4、Set集合类型的属性
	private Set<String> set;
	
	// 学生所学的多门课程
	private List<Course> courseList;
	
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	public void setCourse(String[] course) {
		this.course = course;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	@Override
	public String toString() {
		return "Stu{" +
				"course=" + Arrays.toString(course) +
				", list=" + list +
				", map=" + map +
				", set=" + set +
				", courseList=" + courseList +
				'}';
	}
}
