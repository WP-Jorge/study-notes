package domain;

// import lombok.Data;
import org.springframework.stereotype.Component;

@Component
// @Data
public class Account {
	Integer id;
	String name;
	Double money;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getMoney() {
		return money;
	}
	
	public void setMoney(Double money) {
		this.money = money;
	}
}
