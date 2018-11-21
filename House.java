import java.util.ArrayList;

public class House {
  private static final String[] colors = {"White", "Red", "Brown", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Black"};
  public static final String[] types = {"*", "Book", "Bookshelf", "Computer", "Console", "Display", "Bed"};
  private int color;
  private int floor_count;
  private Floor[] floors;

  private void initializeFloors() {
    for (int i = 0; i < floors.length; i++) floors[i] = new Floor();
  }
  public House() {
    color = 0;
    floor_count = 1;
    floors = new Floor[1];
    initializeFloors();
  }
  public House(int c, int f) {
    color = (c >= 0 && c <= 9 ? c : 0);
    floor_count = (f > 0 ? f : 1);
    floors = new Floor[floor_count];
    initializeFloors();
  }
  public String list(int f, int s, int e, String type) {
    boolean valid_type = false;
    for (String t : types) if (type.equalsIgnoreCase(t)) valid_type = true;
    if (!valid_type) return type + " is not a valid " + Main.bright("yellow", "Item") + " type.";
    if (floors[f].size() == 0) return "Floor is empty!";
    if (!(s < e)) return Main.bright("red", "Start") + " must be less than " + Main.bright("red", "End");
    if (s < 0) return Main.bright("red", "Start") + " must be greater than or equal to " + Main.bright("cyan", "0");
    String ret_val = "\n";
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Integer> item_ids = new ArrayList<Integer>();
    for (int i = s; i < e; i++) {
      if (i > floors[f].size()) continue;
      if (type.equals("*") || type.equalsIgnoreCase(floors[f].getItem(i).subType())) {
        items.add(floors[f].getItem(i));
        item_ids.add(i);
      }
    }
    if (items.size() == 0) return "Floor has no " + Main.bright("yellow", type) + Main.color(" items.");
    for (int i = 0; i < items.size(); i++) {
      ret_val += Main.bright("cyan", Integer.toString(item_ids.get(i))) + ": " + items.get(i).listInfo(true) + Main.bright("yellow", items.get(i).subType()) + items.get(i).listInfo(false);
      if (i < items.size() - 1) ret_val += "\n";
    }
    return ret_val + "\n";
  }
  public String list(int f) {
    return list(f, 0, floors[f].size(), "*");
  }
  public String list(int f, String type) {
    return list(f, 0, floors[f].size(), type);
  }
  public int size() {
    return floor_count;
  }
  public boolean addItem(int f, Item i) {
    if (f >= 0 && f < floor_count) {
      floors[f].addItem(i);
      return true;
    }
    return false;
  }
  public Item getItem(int f, int i) {
    return floors[f].getItem(i);
  }
  public Item getItem(int f, int i, int si) {
    return floors[f].getItem(i, si);
  }
  public Floor getFloor(int f) {
    return floors[f];
  }
  public Floor[] getFloors() {
    return floors;
  }
  public String toString() {
    return "Color: " + colors[color] + "\n" +
           "Floors: " + Main.bright("cyan", Integer.toString(floor_count));
  }
}
