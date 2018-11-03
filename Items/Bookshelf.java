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
      return "\nBook " + b + " removed.\n";
    }
    if (books.size() == 0) return "Bookshelf is already empty!";
    return "Bookshelf only has " + books.size() + " book" + (books.size() > 1 ? "s" : "") + " on it.";
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
    else return " (" + bookCount() + " books)";
  }
  public String toString() {
    String ret_val = "Books in this shelf:";
    for (int i = 0; i < books.size(); i++) ret_val += "\n\t\"" + books.get(i).getTitle() + "\" by \"" + books.get(i).getAuthor() + "\" ID: " + books.get(i).getID();
    ret_val += "\nEnd of Bookshelf contents.";
    return ret_val;
  }
}
