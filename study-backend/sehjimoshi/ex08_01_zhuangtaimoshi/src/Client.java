/**
 * Author Admin
 * Create 2021/6/7 10:20
 */
public class Client {
	public static void main(String[] args) {
		System.out.println("进行登录操作");
		Context content = new Context();
		content.setState("login");
		content.request();
		System.out.println("进行注销操作");
		content.setState("logout");
		content.request();
	}
}
