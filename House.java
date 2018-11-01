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
  public String list(int f, int s, int e) {
    String ret_val = "";
    if (floors[f].size() == 0) return "Floor is empty!";
    for (int i = s; i < e; i++) {
      if (i > floors[f].size()) continue;
      ret_val += i + ": " + floors[f].getItem(i).listInfo(true) + floors[f].getItem(i).type() + floors[f].getItem(i).listInfo(false);
      if (i < e - 1) ret_val += "\n";
    }
    return ret_val;
  }
  public String list(int f) {
    return list(f, 0, floors[f].size());
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
