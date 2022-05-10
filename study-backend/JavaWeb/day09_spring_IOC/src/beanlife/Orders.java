package beanlife;

public class Orders {
	private String oname;
	
	public Orders() {
		System.out.println("第一步 执行无参数的构造函数创建Bean实例");
	}
	
	public void setOname(String oname) {
		this.oname = oname;
		System.out.println("第二部 调用set方法设置属性值");
	}
	
	public void initMethod() {
		// 去bean7里面配置init-method
		System.out.println("第三步 执行初始化方法");
	}
	
	public void destoryMethod() {
		// 去bean7里面配置destory-method
		System.out.println("第五步 执行销毁方法");
	}
}
