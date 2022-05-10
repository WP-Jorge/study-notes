/**
 * Author Admin
 * Create 2021/5/10 11:49
 */
public class Egg extends FoodDecorator {
	private double price = 2;
	private String foodName = "鸡蛋";
	public Egg(Food food) {
		super(food);
	}
	@Override
	public String getFood() {
		return super.getFood() + "，加" + foodName;
	}
	@Override
	public double getPrice() {
		return super.getPrice() + price;
	}
}
