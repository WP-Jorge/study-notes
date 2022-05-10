package model;

public class DVDItem {
	int objectID;
	int year;
	String title;
	String genre;
	
	public DVDItem(int objectID, int year, String title, String genre) {
		this.objectID = objectID;
		this.year = year;
		this.title = title;
		this.genre = genre;
	}
	
	public DVDItem() {
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public int getObjectID() {
		return objectID;
	}
	
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
