import java.util.ArrayList;

public class Table extends Container implements Item {
  private static final String typeS = "Table";

  public Table() {
    super();
  }
  public Table(ArrayList<Item> is, int i) {
    super(is, i);
  }
  public String subType() { return typeS; }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? (size() < 9 ? "Clean" : "Dirty") + " " : " -" + (size() > 0 ? " " + Main.bright("cyan", Integer.toString(size())) + Main.bright("Yellow", " Items") : " Empty"));
  }
  public String toString() {
    String ret_val = Main.color("yellow", "Items") + " on this " + Main.color("yellow", "Table") + ":\n";
    for (int i = 0; i < size(); i++) ret_val += "\t" + Main.bright("cyan", Integer.toString(i)) + ": " + getItem(i).listInfo(true) + getItem(i).subType() + getItem(i).listInfo(false) + "\n";
    return ret_val + "End of " + Main.bright("yellow", "Table") + " contents.";
  }
}
