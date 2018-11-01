public class Viewer {
  private int cur_floor;
  public Item cur_item;
  private House cur_house;

  public Viewer() {
    cur_floor = 0;
    cur_item = new Empty();
    cur_house = new House();
  }
  public Viewer(House h) {
    this();
    cur_house = h;
  }
  public boolean goFloor(int f) {
    if (f >= 0 && f < cur_house.size()) {
      cur_floor = f;
      return true;
    }
    return false;
  }
  public String viewCurItem() {
    return "Object type is: " + cur_item.type() + "\n\n" + cur_item;
  }
  public int floorSize() {
    return cur_house.getFloor(cur_floor).size();
  }
  public String list() {
    return cur_house.list(cur_floor);
  }
  public String list(int s, int e) {
    return cur_house.list(cur_floor, s, e);
  }
  public void addItem(Item i) {
    cur_house.addItem(cur_floor, i);
  }
  public void removeItem(int i) {
    cur_house.getFloor(cur_floor).removeItem(i);
  }
  public int curFloor() {
    return cur_floor;
  }
  public boolean goUp() {
    cur_floor++;
    if (cur_floor >= cur_house.size()) {
      cur_floor--;
      return false;
    }
    return true;
  }
  public boolean goDown() {
    if (cur_floor > 0) {
      cur_floor--;
      return true;
    }
    return false;
  }
  public void changeItemFocus(int i) {
    cur_item = cur_house.getItem(cur_floor, i);
  }
  public void changeItemFocus(int i, int si) {
    cur_item = cur_house.getItem(cur_floor, i, si);
  }
  /*public void changeItemFocus(Item i) {
    cur_item = i;
  }*/
  public void changeHouseFocus(House h) {
    cur_floor = 0;
    cur_item = new Empty();
    cur_house = h;
  }
  public String toString() {
    return "\tCurrent Floor: " + cur_floor + "\n" +
           "\tCurrent Item Type: " + cur_item.type();
  }
}
