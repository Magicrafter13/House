public class Book implements Item {
  private String title;
  private String author;
  private int year;
  private static int total_books = 0;
  private int id;
  private static final String typeS = "Book";

  public Book() {
    title = "none";
    author = "none";
    year = 1600;
    total_books++;
    id = total_books;
  }
  public Book(String t, String a, int y) {
    this();
    title = t;
    author = a;
    if (y >= 1600) year = y;
  }
  public String getTitle() {
    return title;
  }
  public String getAuthor() {
    return author;
  }
  public int getYear() {
    return year;
  }
  public int getID() {
    return id;
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return "";
    else return ": " + title;
  }
  public String toString() {
    return "Title: " + title + "\n" +
           "Author: " + author + "\n" +
           "Year: " + year;
  }
}
