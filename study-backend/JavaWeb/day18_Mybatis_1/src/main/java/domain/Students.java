package domain;

import lombok.Data;

@Data
public class Students {
	private Integer id;
	private String name;
	
	// 学生需要关联一个老师
	private Teacher teacher;
}
