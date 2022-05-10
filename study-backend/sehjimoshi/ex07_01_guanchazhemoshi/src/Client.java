/**
 * Author Admin
 * Create 2021/6/5 15:44
 */
public class Client {
	public static void main(String[] args) {
		Subject goods = new Goods();
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");
		goods.addObserver(user1);
		goods.addObserver(user2);
		goods.addObserver(user3);
		goods.setName("香蕉");
		goods.setPrice(100.0);
		goods.setDiscount(0.75);
	}
}
