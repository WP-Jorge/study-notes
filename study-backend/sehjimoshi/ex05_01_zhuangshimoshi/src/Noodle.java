/**
 * Author Admin
 * Create 2021/5/10 11:20
 */
public class Noodle extends Food {
	private String foodName = "面条";
	private double price = 5;
	@Override
	public String getFood() {
		return this.foodName;
	}
	@Override
	public double getPrice() {
		return this.price;
	}
}
