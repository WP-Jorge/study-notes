package model;

import java.util.List;

public class DVDService {
	private DVDDao dvdDao = new DVDDao();
	public DVDService() {
	}
	public List<DVDItem> getDVDList(String username) {
		return dvdDao.getDVDList(username);
	}
}
