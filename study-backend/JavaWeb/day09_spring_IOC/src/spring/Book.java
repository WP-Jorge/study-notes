package spring;

/**
 * 演示使用set方法注入属性
 */
public class Book {
	// 创建属性
	private String bname;
	private String bauthor;
	
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	@Override
	public String toString() {
		return "Book{" +
				"bname='" + bname + '\'' +
				", bauthor='" + bauthor + '\'' +
				'}';
	}
}
