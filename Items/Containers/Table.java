import java.util.ArrayList;

public class Table extends Container implements Item {
  private static final String typeS = "Table";

  public Table() {
    super();
  }
  public Table(ArrayList<Item> is, int i) {
    super(is, i);
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if (size() == 0 && key.equalsIgnoreCase("Empty"))
        output += listInfo(true) + typeS + listInfo(false);
    if (output.equals("")) {
      for (int i = 0; i < size(); i++) {
        String temp = super.getItem(i).search(keywords);
        if (output.equals("") && !temp.equals("")) output = "Table:\n";
        if (!temp.equals("")) output += "\t" + i + ": " + temp + "\n";
      }
    }
    return output;
  }
  public String export(int space) {
    String retStr = "";
    for (int i = 0; i < space; i++) retStr += " ";
    retStr += "new Table(new ArrayList<Item>(Arrays.asList( " + (super.getItems().size() > 0 ? "\n" : " ");
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
    return "new Table(new ArrayList<Item>(Arrays.asList( /*Items on Table*/ )), " + super.getRoom() + "),";
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
