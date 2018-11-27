import java.util.ArrayList;

public class Container implements Item {
  private ArrayList<Item> items = new ArrayList<Item>();
  private static final String typeS = "Container";

  public Container() {
    items = new ArrayList<Item>();
  }
  public Container(ArrayList<Item> is) {
    items = is;
  }
  public Item getItem(int i) { return (i < 0 || i >= items.size() ? new Empty() : items.get(i)); }
  public String addItem(Item i) {
    if (i == this) return "You can't put something inside itself!";
    if (!hasItem(i)) items.add(i);
    else return "That " + Main.bright("yellow", "Item") + " is already in this " + typeS + "! (I don't think this message should be able to be seen.)";
    return Main.bright("yellow", "\nItem") + Main.color("blue", " added") + " to this " + Main.bright("yellow", typeS) + ".\n";
  }
  public String removeItem(int i) {
    if (i < 0 || i >= items.size()) return "This" + Main.bright("Yellow", typeS) + (items.size() > 0 ? " only has " + Main.bright("cyan", Integer.toString(items.size())) + " items in it." : " is " + Main.bright("yellow", "Empty"));
    items.remove(i);
    return Main.bright("yellow", "\nItem ") + Main.color("blue", "removed") + ".\n";
  }
  public String removeItem(Item i) {
    for (int g = 0; g < items.size(); g++) {
      if (items.get(g) != i) continue;
      removeItem(g);
      return Main.bright("yellow", "\nItem ") + Main.color("blue", "removed") + ".\n";
    }
    return "No matching " + Main.bright("yellow", "Item") + " found.";
  }
  public int size() { return items.size(); }
  public boolean hasItem(Item test) {
    for (Item i : items) if (i == test) return true;
    return false;
  }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? "" : ", " + (items.size() > 0 ? Main.bright("cyan", Integer.toString(items.size())) + Main.bright("yellow", " Items") : Main.color("yellow", "Empty")));
  }
  public String toString() {
    String ret_val = Main.bright("yellow", typeS);
    for (int i = 0; i < items.size(); i++) ret_val += "\t" + Main.bright("cyan", Integer.toString(i)) + ": " + items.get(i).listInfo(true) + items.get(i) + items.get(i).listInfo(false) + "\n";
    return ret_val + "End of " + Main.bright("yellow", "Container") + " contents.";
  }
}
