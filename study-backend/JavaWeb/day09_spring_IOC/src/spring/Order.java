package spring;

/**
 * 有参构造注入
 */
public class Order {
	private String oname;
	private String address;
	
	public Order(String oname, String address) {
		this.oname = oname;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Order{" +
				"oname='" + oname + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
