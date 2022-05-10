package dao;

public interface UserDao {
	public int reduceMoney(String name, Double money);
	
	public int addMoney(String name, Double money);
}
