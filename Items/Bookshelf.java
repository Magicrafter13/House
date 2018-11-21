import java.util.ArrayList;

public class Bookshelf extends Container implements Item {
  //private ArrayList<Book> items;
  private static final String typeS = "Bookshelf";

  public Bookshelf() {
    super();
  }
  public Bookshelf(ArrayList<Book> b) {
    super();
    for (Book a : b) addItem(a);
  }
  public String addItem(Item i) {
    if (i instanceof Book) {
      super.addItem(i);
      return Main.bright("yellow", "\nBook") + Main.color("blue", " added") + " to " + Main.bright("yellow", "Bookshelf") + ".\n";
    } else return Main.bright("yellow", "Item") + " is not a " + Main.bright("yellow", "Book") + ".";
  }
  public String removeItem(int i) {
    if (i >= 0 && i < size()) {
      super.removeItem(i);
      return Main.bright("yellow", "\nBook ") + i + Main.color("blue", " removed") + ".\n";
    }
    if (size() == 0) return Main.bright("yellow", "Bookshelf") + " is already empty!";
    return Main.bright("yellow", "Bookshelf") + " only has " + Main.bright("cyan", Integer.toString(size())) + (size() > 1 ? Main.color("yellow", " Books") : Main.bright("yellow", " Book")) + " on it.";
  }
  public String removeItem(Item i) {
    for (int t = 0; t < size(); t++) {
      if (getItem(t) == i) {
        super.removeItem(i);
        return Main.bright("yellow", "\nBook") + ", " + Main.color("blue", "removed") + ".\n";
      }
    }
    return "No matching " + Main.bright("yellow", "Book") + " found.";
  }
  public String subType() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return size() > 0 ? "" : "Empty ";
    if (size() > 0) return " (" + Main.bright("cyan", Integer.toString(size())) + (size() > 1 ? Main.color("yellow", " Books") : Main.bright("yellow", " Book")) + ")";
    return "";
  }
  public String toString() {
    String ret_val = Main.color("yellow", "Books") + " in this " + Main.color("yellow", "shelf") + ":";
    for (int i = 0; i < size(); i++) ret_val += "\n\t" + Main.bright("cyan", Integer.toString(i)) + ": \"" + ((Book)getItem(i)).getTitle() + "\" by \"" + ((Book)getItem(i)).getAuthor() + "\" ID: " + ((Book)getItem(i)).getID();
    return ret_val + "\nEnd of " + Main.bright("yellow", "Bookshelf") + " contents.";
  }
}
