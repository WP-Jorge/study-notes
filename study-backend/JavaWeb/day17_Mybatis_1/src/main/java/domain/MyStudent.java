package domain;

public class MyStudent {
	private Integer stuid;
	private String stuname;
	private String stueamil;
	private Integer stuage;
	
	public Integer getStuid() {
		return stuid;
	}
	
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	
	public String getStuname() {
		return stuname;
	}
	
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	
	public String getStueamil() {
		return stueamil;
	}
	
	public void setStueamil(String stueamil) {
		this.stueamil = stueamil;
	}
	
	public Integer getStuage() {
		return stuage;
	}
	
	public void setStuage(Integer stuage) {
		this.stuage = stuage;
	}
	
	@Override
	public String toString() {
		return "MyStudent{" +
				"stuid=" + stuid +
				", stuname='" + stuname + '\'' +
				", stueamil='" + stueamil + '\'' +
				", stuage=" + stuage +
				'}';
	}
}
