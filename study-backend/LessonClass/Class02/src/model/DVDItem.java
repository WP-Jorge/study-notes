package model;

public class DVDItem {
    String title;
    String year;
    String genre;

    public DVDItem(String title, String year, String genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "DVDItem{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
