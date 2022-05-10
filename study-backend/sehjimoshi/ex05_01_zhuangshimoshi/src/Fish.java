/**
 * Author Admin
 * Create 2021/5/10 11:49
 */
public class Fish extends FoodDecorator {
	private double price = 5;
	private String foodName = "鱼排";
	public Fish(Food food) {
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
