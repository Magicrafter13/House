import java.util.ArrayList;

public class Viewer {
  private int cur_floor;
  public Item cur_item;
  private House cur_house;
  private int cur_room;

  public Viewer() {
    cur_floor = 0;
    cur_item = new Empty();
    cur_house = new House();
  }
  public Viewer(House h) {
    this();
    cur_house = h;
  }
  public String search(int floor, int room, String item, ArrayList<String> keywords) {
    return cur_house.search(room != -2 && floor == -1 ? cur_floor : floor, room, item, keywords);
  }
  public int goRoom(int r) {
    if (r < -1) return 1;
    ArrayList<String> rooms = roomNames();
    if (r >= rooms.size()) return 2;
    cur_room = r;
    if (r == -1) return 3;
    return 0;
  }
  public ArrayList<String> roomNames() {
    return cur_house.getFloor(cur_floor).rooms();
  }
  public House curHouse() {
    return cur_house;
  }
  public boolean isItem(int i) { return (i >= 0 && i < cur_house.getFloor(cur_floor).size()); }
  public Item getItem(int i) { return cur_house.getItem(cur_floor, i); }
  public boolean goFloor(int f) {
    boolean check = (f >= 0 && f < cur_house.size());
    if (check) cur_floor = f;
    return check;
  }
  public String viewCurItem() {
    return "Object type is: " + Main.bright("yellow", cur_item.type()) + "\n\n" + cur_item;
  }
  public int floorSize() { return cur_house.getFloor(cur_floor).size(); }
  public int pageCount(int s, int e, String t, int l) { return cur_house.pageCount(cur_floor, s, e, t, l); }
  public String list(int s, int e, String t, int l, int p, int r) {
    return cur_house.list(cur_floor, s, e, t, l, p, r);
  }
  //public String list() { return cur_house.list(cur_floor); }
  //public String list(int s, int e) { return cur_house.list(cur_floor, s, e, "*", floorSize(), 0); }
  //public String list(String type) { return cur_house.list(cur_floor, type); }
  public void addItem(Item i) { cur_house.addItem(cur_floor, i); }
  public void removeItem(int in, int sin) {
    Item temp = cur_house.getFloor(cur_floor).getItem(in, sin);
    if (temp == cur_item) cur_item = new Empty();
    if (temp.hasItem(cur_item)) if (!(temp instanceof Display)) cur_item = new Empty();
    //any Item that can have this item will have it removed - currently no sub items have their own sub items
    if (cur_house.getFloor(cur_floor).removeItem(in, sin)) {
      for (Floor f : cur_house.getFloors()) {
        for (Item i : f.getItems()) {
          if (i.hasItem(temp)) {
            switch (i.type()) {
              case "Container": ((Container)i).removeItem(temp); break;
              case "Display": ((Display)i).disconnect(temp); break;
            }
          }
        }
      }
    }
  }
  public void removeItem(Item in) {
    Item temp = in;
    if (temp == cur_item) cur_item = new Empty();
    if (temp.hasItem(cur_item)) if (!(temp instanceof Display)) cur_item = new Empty();
    //any Item that can have this item will have it removed
    for (Floor f : cur_house.getFloors()) {
      for (Item i : f.getItems()) {
        if (i.hasItem(temp)) {
          switch (i.type()) {
            case "Container": ((Container)i).removeItem(temp); break;
            case "Display": ((Display)i).disconnect(temp); break;
          }
        }
      }
    }
    cur_house.getFloor(cur_floor).removeItem(in);
  }
  public void removeItem(int in) { removeItem(cur_house.getFloor(cur_floor).getItem(in)); }
  public int curFloor() { return cur_floor; }
  public String goUp() {
    cur_floor++;
    if (cur_floor >= cur_house.size()) {
      cur_floor--;
      return "\nYou are currently on the top floor, floor unchanged.\n";
    }
    return "\nWelcome to floor " + Main.bright("cyan", Integer.toString(cur_floor)) + ".\n";
  }
  public String goDown() {
    if (cur_floor > 0) {
      cur_floor--;
      return "\nWelcome to floor " + Main.bright("cyan", Integer.toString(cur_floor)) + ".\n";
    }
    return "\nYou are currently on the bottom floor, floor unchanged.\n";
  }
  public boolean changeItemFocus(int i) {
    boolean check = (i >= 0 && i < cur_house.getFloor(cur_floor).size());
    if (check) cur_item = cur_house.getItem(cur_floor, i);
    return check;
  }
  public int changeItemFocus(int i, int si) {
    if (i >= 0 && i < cur_house.getFloor(cur_floor).size()) {
      cur_item = cur_house.getItem(cur_floor, i, si);
      return (cur_item instanceof Empty ? 1 : 0);
    }
    return 2;
  }
  public void changeHouseFocus(House h) {
    cur_floor = 0;
    cur_item = new Empty();
    cur_house = h;
  }
  public String toString() {
    return "\tCurrent House: " + Main.house + "\n" +
           "\tCurrent Floor: " + cur_floor + "\n" +
           "\tCurrent Item Type: " + Main.bright("yellow") + cur_item.type() + Main.ANSI_RESET;
  }
}
