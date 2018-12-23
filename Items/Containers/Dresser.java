import java.util.ArrayList;

public class Dresser extends Container implements Item {
  private static final String typeS = "Dresser";

  public Dresser() {
    super();
  }
  public Dresser(ArrayList<Item> is, int i) {
    super(is, i);
  }
  public String export(int space) {
    String retStr = "";
    for (int i = 0; i < space; i++) retStr += " ";
    retStr += "new Dresser(new ArrayList<Item>(Arrays.asList( " + (super.getItems().size() > 0 ? "\n" : " ");
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
    return "new Dresser(new ArrayList<Item>(Arrays.asList( /*Items in Dresser*/ )), " + super.getRoom() + "),";
  }
  public boolean hasClothes(String type) {
    for (int i = 0; i < size(); i++) if (getItem(i).subType().equalsIgnoreCase(type)) return true;
    return false;
  }
  public String subType() { return typeS; }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? "" : " [" + (size() > 0 ? Main.bright("cyan", Integer.toString(size())) + " pieces of " + Main.bright("yellow", "Clothing") : Main.color("yellow", "Empty")) + "]");
  }
  public String toString() {
    String ret_val = Main.color("yellow", "Items") + " in this " + Main.bright("yellow", "Dresser") + ":";
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();
    for (int i = 0; i < size(); i++) {
      String tempType = getItem(i).subType();
      boolean isNewType = true;
      for (int s = 0; s < type.size(); s++) {
        if (type.get(s).equalsIgnoreCase(tempType)) {
          isNewType = false;
          int temp_int = count.get(s);
          count.remove(s);
          count.add(s, temp_int + 1);
        }
      }
      if (isNewType) {
        type.add(tempType);
        count.add(1);
      }
    }
    for (int i = 0; i < type.size(); i++) ret_val += "\n\t" + type.get(i) + ": " + count.get(i);
    return ret_val + "\nEnd of " + Main.bright("yellow", "Dresser") + " contents.";
  }
}
