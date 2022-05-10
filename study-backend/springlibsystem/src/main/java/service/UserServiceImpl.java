package service;

import dao.UserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:03 2021/4/21
 * @Param 
 * @return 
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public User getUser(User user) {
		return userDao.getUser(user);
	}
	
	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}
	
	@Override
	public int borrowBooksCount(Integer uid) {
		return userDao.borrowBooksCount(uid);
	}
}
