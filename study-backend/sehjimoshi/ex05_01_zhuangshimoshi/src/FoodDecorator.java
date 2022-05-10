import java.util.ArrayList;
import java.util.List;

/**
 * Author Admin
 * Create 2021/5/10 11:26
 */
public class FoodDecorator extends Food {
	private Food food;
	public FoodDecorator(Food food) {
		this.food = food;
	}
	@Override
	public String getFood() {
		return food.getFood();
	}
	@Override
	public double getPrice() {
		return food.getPrice();
	}
}
