package domain;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {
	private Integer tid;
	String name;
	// 一个老师拥有多个学生
	private List<Students> students;
}
