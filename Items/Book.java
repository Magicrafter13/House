import java.util.ArrayList;

public class Book implements Item {
  private String title;
  private String author;
  private int year;
  private static int total_books = 0;
  private int id;
  private static final String typeS = "Book";
  private int roomID;

  public Book() {
    title = "none";
    author = "none";
    year = 1600;
    total_books++;
    id = total_books;
    roomID = -1;
  }
  public Book(String t, String a, int y, int i) {
    this();
    title = t;
    author = a;
    if (y >= 1600) year = y;
    roomID = i;
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if (key.equalsIgnoreCase(title) || key.equalsIgnoreCase(author) || key.equals(year))
        output += listInfo(true) + typeS + listInfo(false);
    return output;
  }
  public String export() {
    return "new Book(\"" + title + "\", \"" + author + "\", " + year + ", " + roomID + "),";
  }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) { return false; }
  public void reset(String t, String a, int y) {
    title = t;
    author = a;
    year = (y >= 1600 ? y : 1600);
  }
  public String getTitle() { return title; }
  public String getAuthor() { return author; }
  public int getYear() { return year; }
  public int getID() { return id; }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) { return (before_not_after ? "" : ": " + title); }
  public String toString() {
    return "Title: " + title + "\n" +
           "Author: " + author + "\n" +
           "Year: " + year;
  }
}
