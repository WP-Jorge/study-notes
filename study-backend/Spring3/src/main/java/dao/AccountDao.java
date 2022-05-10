package dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AccountDao {
	public int reduceMoney(@Param("name") String name, @Param("money") Double money);
	
	public int addMoney(@Param("name") String name, @Param("money") Double money);
}
