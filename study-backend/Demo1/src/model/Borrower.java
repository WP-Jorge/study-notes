package model;

public class Borrower {
	String name = "";
	int age = 0;
	String sex = "";
	String title = "";
	
	Borrower(String name) {
		this.name = name;
	}
	
	Borrower(String name, int age, String sex, String title) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.title = title;
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
}
