import java.util.ArrayList;

public class House {
  public static final String[] colors = {"White", "Red", "Brown", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Black"};
  public static final String[] types = {"*", "Bed", "Book", "Computer", "Console", "Display",
                                        "Bookshelf", "Container", "Dresser", "Fridge", "Table",
                                        "Clothing", "Pants", "Shirt"};
  private int color;
  private int floor_count;
  private Floor[] floors;
  private void initializeFloors() { for (int i = 0; i < floors.length; i++) floors[i] = new Floor(); }

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
  public House(int c, Floor[] fs) {
    color = (c >= 0 && c <= 9 ? c : 0);
    floor_count = fs.length;
    floors = fs;
  }
  public int pageCount(int f, int rangeStart, int rangeEnd, String searchType, int pageLength) {
    boolean valid_type = false;
    for (String t : types) if (searchType.equalsIgnoreCase(t)) valid_type = true;
    if (!valid_type) return -1;
    if (floors[f].size() == 0) return -2;
    if (!(rangeStart < rangeEnd)) return -3;
    if (rangeStart < 0) return -4;
    int items = 0;
    for (int i = rangeStart; i < rangeEnd; i++) {
      if (i > floors[f].size()) continue;
      if (searchType.equals("*") ||
          searchType.equalsIgnoreCase(floors[f].getItem(i).subType()) ||
          searchType.equalsIgnoreCase(floors[f].getItem(i).type())) {
        items++;
      }
    }
    return (items / pageLength + (items % pageLength == 0 ? 0 : 1));
  }
  public String list(int f, int s, int e, String type, int pageLength, int page, int room) {
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
      if ((
          type.equals("*") ||
          type.equalsIgnoreCase(floors[f].getItem(i).subType()) ||
          type.equalsIgnoreCase(floors[f].getItem(i).type())
        ) && (room == -2 || floors[f].getItem(i).getRoom() == room)) {
        items.add(floors[f].getItem(i));
        item_ids.add(i);
      }
    }
    if (items.size() == 0) return "Floor has no " + Main.bright("yellow", type) + Main.color("yellow", " items.");
    for (int i = pageLength * page; i < pageLength * (page + 1); i++) {
      if (i >= items.size()) continue;
      ret_val += Main.bright("cyan", Integer.toString(item_ids.get(i))) + ": " + items.get(i).listInfo(true) + Main.bright("yellow", items.get(i).subType()) + items.get(i).listInfo(false);
      if (i < items.size() - 1) ret_val += "\n";
    }
    return ret_val + "\n";
  }
  //public String list(int f) { return list(f, 0, floors[f].size(), "*", floors[f].size(), 0); }
  //public String list(int f, String type) { return list(f, 0, floors[f].size(), type, floors[f].size(), 0); }
  public int size() { return floor_count; }
  public boolean addItem(int f, Item i) {
    boolean check = (f >= 0 && f < floor_count);
    if (check) floors[f].addItem(i);
    return check;
  }
  public Item getItem(int f, int i) { return floors[f].getItem(i); }
  public Item getItem(int f, int i, int si) { return floors[f].getItem(i, si); }
  public Floor getFloor(int f) { return floors[f]; }
  public Floor[] getFloors() { return floors; }
  public String toString() {
    return "Color: " + colors[color] + "\n" +
           "Floors: " + Main.bright("cyan", Integer.toString(floor_count));
  }
}
