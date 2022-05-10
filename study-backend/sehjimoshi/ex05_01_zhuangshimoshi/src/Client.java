/**
 * Author Admin
 * Create 2021/5/10 11:51
 */
public class Client {
	public static void main(String[] args) {
		Food food = new Noodle();
		food = new Chops(food);
		food = new Fish(food);
		food = new Egg(food);
		System.out.println(food.getFood());
		System.out.println("总计：" + food.getPrice() + "元");
	}
}
