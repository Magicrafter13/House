import java.util.ArrayList;

public class House {
  private static final String[] colors = {"White", "Red", "Brown", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Black"};
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
    if (floors[f].size() == 0) return "Floor is empty!";
    String ret_val = "";
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Integer> item_ids = new ArrayList<Integer>();
    for (int i = s; i < e; i++) {
      if (i > floors[f].size()) continue;
      if (type.equals("*") || type.equalsIgnoreCase(floors[f].getItem(i).type())) {
        items.add(floors[f].getItem(i));
        item_ids.add(i);
      }
    }
    for (int i = 0; i < items.size(); i++) {
      ret_val += item_ids.get(i) + ": " + items.get(i).listInfo(true) + items.get(i).type() + items.get(i).listInfo(false);
      if (i < items.size() - 1) ret_val += "\n";
    }
    /*for (int i = s; i < e; i++) {
      if (i > floors[f].size()) continue;
      if (type.equalsIgnoreCase("*") || type.equalsIgnoreCase(floors[f].getItem(i).type())) {
        ret_val += i + ": " + floors[f].getItem(i).listInfo(true) + floors[f].getItem(i).type() + floors[f].getItem(i).listInfo(false);
        if (i < e - 1) ret_val += "\n";
      }
    }*/
    return ret_val;
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
  public String toString() {
    return "Color: " + colors[color] + "\n" +
           "Floors: " + floor_count;
  }
}
