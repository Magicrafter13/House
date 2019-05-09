import java.util.ArrayList;

public class Bookshelf extends Container implements Item {
  private static final String typeS = "Bookshelf";

  public Bookshelf() {
    super();
  }
  public Bookshelf(ArrayList<Book> b, int i) {
    super(new ArrayList<Item>(), i);
    for (Book a : b) addItem(a);
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if (size() == 0 && key.equalsIgnoreCase("Empty"))
        output += listInfo(true) + typeS + listInfo(false);
    if (output.equals("")) {
      for (int i = 0; i < size(); i++) {
        String temp = super.getItem(i).search(keywords);
        if (output.equals("") && !temp.equals("")) output = "Bookshelf:\n";
        if (!temp.equals("")) output += "\t" + i + ": " + temp + "\n";
      }
    }
    return output;
  }
  public String export(int space) {
    String retStr = "";
    for (int i = 0; i < space; i++) retStr += " ";
    retStr += "new Bookshelf(new ArrayList<Item>(Arrays.asList( " + (super.getItems().size() > 0 ? "\n" : " ");
    for (int i = 0; i < super.getItems().size(); i++) {
      if (super.getItem(i) instanceof Container) {
        switch (super.getItem(i).subType()) {
          case "Bookshelf": retStr += ((Bookshelf)super.getItem(i)).export(space + 2); break;
          case "Dresser": retStr += ((Dresser)super.getItem(i)).export(space + 2); break;
          case "Fridge": retStr += ((Fridge)super.getItem(i)).export(space + 2); break;
          case "Table": retStr += ((Table)super.getItem(i)).export(space + 2); break;
          default: retStr += ((Container)super.getItem(i)).export(space + 2); break;
        }
        continue;
      }
      if (super.getItem(i) instanceof Display) {
        retStr += ((Display)super.getItem(i)).export(space + 2) + "\n";
        continue;
      }
      for (int s = 0; s < space + 2; s++) retStr += " ";
      retStr += super.getItem(i).export() + "\n";
    }
    if (super.getItems().size() > 0)
      for (int i = 0; i < space; i++)
        retStr += " ";
    return retStr + ")), " + super.getRoom() + "),\n";
  }
  public String export() {
    return "new Bookshelf(new ArrayList<Book>(Arrays.asList( /*Books in Bookshelf*/ )), " + super.getRoom() + "),";
  }
  public String addItem(Item i) {
    if (i instanceof Book) {
      super.addItem(i);
      return Main.bright("yellow", "\nBook") + Main.color("blue", " added") + " to " + Main.bright("yellow", "Bookshelf") + ".\n";
    } else return Main.bright("yellow", "Item") + " is not a " + Main.bright("yellow", "Book") + ".";
  }
  public String removeItem(int i) {
    if (size() == 0) return Main.bright("yellow", "Bookshelf") + " is already empty!";
    if (i >= 0 && i < size()) {
      super.removeItem(i);
      return Main.bright("yellow", "\nBook ") + i + Main.color("blue", " removed") + ".\n";
    }
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
  public String subType() { return typeS; }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? (size() > 0 ? "" : "Empty ") : (size() > 0 ? " (" + Main.bright("cyan", Integer.toString(size())) + (size() > 1 ? Main.color("yellow", " Books") : Main.bright("yellow", " Book")) + ")" : ""));
  }
  public String toString() {
    String ret_val = Main.color("yellow", "Books") + " in this " + Main.color("yellow", "shelf") + ":";
    for (int i = 0; i < size(); i++) ret_val += "\n\t" + Main.bright("cyan", Integer.toString(i)) + ": \"" + ((Book)getItem(i)).getTitle() + "\" by \"" + ((Book)getItem(i)).getAuthor() + "\" ID: " + ((Book)getItem(i)).getID();
    return ret_val + "\nEnd of " + Main.bright("yellow", "Bookshelf") + " contents.";
  }
}
