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
  public void addBook(Book b) {
    books.add(b);
  }
  public String removeBook(int b) {
    if (b >= 0 && b < books.size()) {
      books.remove(b);
      return Main.bright("yellow") + "\nBook " + Main.ANSI_RESET + b + " removed.\n";
    }
    if (books.size() == 0) return Main.bright("yellow") + "Bookshelf" + Main.ANSI_RESET + " is already empty!";
    return Main.bright("yellow") + "Bookshelf" + Main.ANSI_RESET + " only has " + books.size() + Main.bright("yellow") + " Book" + Main.ANSI_RESET + (books.size() > 1 ? "s" : "") + " on it.";
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
    if (bookCount() > 0) return " (" + bookCount() + Main.bright("yellow") + " Book" + Main.ANSI_RESET + (bookCount() > 1 ? "s" : "") + ")";
    return "";
  }
  public String toString() {
    String ret_val = Main.bright("yellow") + "Book" + Main.ANSI_RESET + "s in this shelf:";
    for (int i = 0; i < books.size(); i++) ret_val += "\n\t\"" + books.get(i).getTitle() + "\" by \"" + books.get(i).getAuthor() + "\" ID: " + books.get(i).getID();
    return ret_val + "\nEnd of " + Main.bright("yellow") + "Bookshelf" + Main.ANSI_RESET + " contents.";
    //ret_val += "\nEnd of " + Main.bright("yellow") + "Bookshelf" + Main.ANSI_RESET + " contents.";
    //return ret_val;
  }
}
