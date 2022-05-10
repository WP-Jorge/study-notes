package dao;

public interface AccountDao {
	public int reduceMoney(String name, Double money);
	
	public int addMoney(String name, Double money);
}
