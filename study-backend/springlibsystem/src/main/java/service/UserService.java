package service;

import domain.User;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:03 2021/4/21
 * @Param 
 * @return 
 */
public interface UserService {
	/**
	 * [获取用户]
	 * @param user
	 * @return
	 */
	User getUser(User user);
	
	/**
	 * [添加用户]
	 * @param user
	 * @return
	 */
	int addUser(User user);
	
	/**
	 * [根据用户id查询借阅数量]
	 * @param uid
	 * @return
	 */
	int borrowBooksCount(Integer uid);
	
}
