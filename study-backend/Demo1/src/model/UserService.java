package model;

import java.util.List;

public class UserService {
	private UserDao userDao = new UserDao();
	public UserService() {
	}
	public List<User> getUser(String username, String password) {
		return userDao.getUser(username, password);
	}
}
