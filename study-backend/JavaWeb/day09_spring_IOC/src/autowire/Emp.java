package autowire;

public class Emp {
	private Dept dept;
	
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	@Override
	public String toString() {
		return "Dept{" +
				"dept=" + dept +
				'}';
	}
	
	private void test() {
		System.out.println(dept);
	}
}
