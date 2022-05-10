package spring;

public class Student {
	private String sname;
	private String sex;
	private String habit;
	
	@Override
	public String toString() {
		return "Student{" +
				"sname='" + sname + '\'' +
				", sex='" + sex + '\'' +
				", habit='" + habit + '\'' +
				'}';
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setHabit(String habit) {
		this.habit = habit;
	}
}
