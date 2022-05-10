import java.util.ArrayList;
import java.util.List;

/**
 * Author Admin
 * Create 2021/6/5 15:59
 */
public abstract class Subject {
	private String name;
	private Double price = 0.0;
	private Double discount = 1.0;
	private List<Observer> observers = new ArrayList<>();
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void delObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObserver(String msg) {
		for (Observer observer : observers) {
			observer.updateMsg(msg);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price * discount;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return "Goods{" +
				"name='" + name + '\'' +
				", price=" + price +
				", discount=" + discount +
				'}';
	}
}
