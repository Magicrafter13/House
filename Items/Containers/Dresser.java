import java.util.ArrayList;

public class Dresser extends Container implements Item {
  private static final String typeS = "Dresser";

  public Dresser() {
    super();
  }
  public Dresser(ArrayList<Item> is) {
    super(is);
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
