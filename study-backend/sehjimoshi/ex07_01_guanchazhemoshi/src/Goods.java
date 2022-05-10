import java.util.ArrayList;
import java.util.List;

/**
 * Author Admin
 * Create 2021/6/5 15:26
 */
public class Goods extends Subject {
	
	private String name;
	
	private Double price = 0.0;
	
	private Double discount = 1.0;
	
	private List<Observer> observers = new ArrayList<>();
	
	public Goods(String name, Double price, Double discount, List<Observer> observers) {
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.observers = observers;
	}
	
	public Goods() {
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setPrice(Double price) {
		this.price = price * discount;
		this.notifyObserver(this.name + "的价格发生变化 -》折扣为" + this.discount + "，价格为：" + this.price);
	}
	
	@Override
	public void setDiscount(Double discount) {
		this.discount = discount;
		this.price = price * discount;
		this.notifyObserver(this.name + "的价格发生变化 -》折扣为" + this.discount + "，价格为：" + this.price);
	}
	
}
