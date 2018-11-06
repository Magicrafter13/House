import java.util.ArrayList;

public class Bookshelf implements Item {
  private ArrayList<Book> books;
  private static final String typeS = "Bookshelf";

  public Bookshelf() {
    books = new ArrayList<Book>();
  }
  public Bookshelf(ArrayList<Book> b) {
    this();
    books = b;
  }
  public boolean hasItem(Item test) {
    if (test instanceof Book) for (Book b : books) if (test == b) return true;
    return false;
  }
  public void addBook(Book b) {
    books.add(b);
  }
  public String removeBook(int b) {
    if (b >= 0 && b < books.size()) {
      books.remove(b);
      return Main.bright("yellow", "\nBook ") + b + Main.color("blue", " removed") + ".\n";
    }
    if (books.size() == 0) return Main.bright("yellow", "Bookshelf") + " is already empty!";
    return Main.bright("yellow", "Bookshelf") + " only has " + Main.bright("cyan", Integer.toString(books.size())) + (books.size() > 1 ? Main.color("yellow", " Books") : Main.bright("yellow", " Book")) + " on it.";
  }
  public String removeBook(Item b) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i) == b) {
        books.remove(b);
        return Main.bright("yellow", "\nBook") + ", " + Main.color("blue", "removed") + ".\n";
      }
    }
    return "No matching " + Main.bright("yellow", "Book") + " found.";
  }
  public int bookCount() {
    return books.size();
  }
  public Book getBook(int i) {
    return books.get(i);
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return bookCount() > 0 ? "" : "Empty ";
    if (bookCount() > 0) return " (" + Main.bright("cyan", Integer.toString(bookCount())) + (bookCount() > 1 ? Main.color("yellow", " Books") : Main.bright("yellow", " Book")) + ")";
    return "";
  }
  public String toString() {
    String ret_val = Main.color("yellow", "Books") + " in this " + Main.color("yellow", "shelf") + ":";
    for (int i = 0; i < books.size(); i++) ret_val += "\n\t\"" + books.get(i).getTitle() + "\" by \"" + books.get(i).getAuthor() + "\" ID: " + books.get(i).getID();
    return ret_val + "\nEnd of " + Main.bright("yellow", "Bookshelf") + " contents.";
  }
}
