import java.util.ArrayList;

public class Floor {
  private ArrayList<Item> items = new ArrayList<Item>();
  private boolean lights = false;
  private ArrayList<String> roomNames = new ArrayList<String>();

  public Floor() {
    this(new ArrayList<Item>(), false, new ArrayList<String>());
  }
  public Floor(ArrayList<Item> i, boolean l, ArrayList<String> n) {
    items = i;
    lights = l;
    roomNames = n;
  }
  public String export(int floor) {
    String retStr = "  Floor " + Integer.toString(floor) + "\n    Room Names = { \"" + roomNames.get(0) + "\"";
    for (int i = 1; i < roomNames.size(); i++) retStr += ", \"" + roomNames.get(i) + "\"";
    retStr += " }\n";
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i) instanceof Container) {
        switch (items.get(i).subType()) {
          case "Bookshelf": retStr += ((Bookshelf)items.get(i)).export(4); break;
          case "Dresser": retStr += ((Dresser)items.get(i)).export(4); break;
          case "Fridge": retStr += ((Fridge)items.get(i)).export(4); break;
          case "Table": retStr += ((Table)items.get(i)).export(4); break;
          default: retStr += ((Container)items.get(i)).export(4); break;
        }
        continue;
      }
      if (items.get(i) instanceof Display) {
        retStr += ((Display)items.get(i)).export(4);
        continue;
      }
      retStr += "    " + items.get(i).export() + "\n";
    }
    return retStr + "  End Floor " + floor + "\n";
  }
  public void addRoom(String r) {
    roomNames.add(r);
  }
  public ArrayList<String> rooms() {
    return new ArrayList<String>(roomNames);
  }
  /*public Floor(ArrayList<Item> i) {
    this(i, false, new ArrayList<String>());
  }
  public Floor(boolean l) {
    this(new ArrayList<Item>(), l, new ArrayList<String>());
  }
  public Floor(ArrayList<String> n) {
    this(new ArrayList<Item>(), false, n);
  }
  public Floor(ArrayList<Item> i, boolean l) {
    this(i, l, new ArrayList<String>());
  }
  public Floor(ArrayList<Item> i, ArrayList<String> n) {
    this(i, false, n);
  }
  public Floor(boolean l, ArrayList<String> n) {
    this(new ArrayList<Item>(), l, n);
  }*/
  public boolean getLights() { return lights; }
  public String toggleLights() {
    lights = (lights ? false : true);
    return "Lights turned " + (lights ? "on" : "off") + ".";
  }
  public void addItem(Item i) { items.add(i); }
  public void removeItem(int i) { items.remove(i); }
  public void removeItem(Item test) {
    for (int i = 0; i < items.size(); i++) if (items.get(i) == test) items.remove(i);
  }
  public boolean removeItem(int in, int sin) {
    int remove_from_floor = -1;
    for (int i = 0; i < items.size(); i++) {
      switch(items.get(in).type()) {
        case "Container": case "Fridge": case "Bookshelf":
          if (((Container)items.get(in)).getItem(sin) == items.get(i)) remove_from_floor = i;
          break;
        case "Display":
          if (((Display)items.get(in)).getDevice(sin) == items.get(i)) remove_from_floor = i;
          break;
      }
    }
    switch (items.get(in).type()) {
      case "Container": case "Fridge": case "Bookshelf": ((Container)items.get(in)).removeItem(sin); break;
      case "Display": ((Display)items.get(in)).disconnect(sin); break;
      default: return false;
    }
    if (remove_from_floor > -1) items.remove(remove_from_floor);
    return true;
  }
  public ArrayList<Item> getItems() { return items; }
  public Item getItem(int i) { return ((i >= 0 && i < items.size()) ? items.get(i) : new Empty()); }
  public Item getItem(int i, int si) {
    Item ret_val = new Empty();
    if (i >= 0 && i < items.size()) {
      if (items.get(i) instanceof Container) ret_val = ((Container)items.get(i)).getItem(si);
      if (items.get(i) instanceof Display) ret_val = ((Display)items.get(i)).getDevice(si);
    }
    return ret_val;
  }
  public int size() { return items.size(); }
  public String toString() {
    return "This floor has " + Main.bright("cyan", Integer.toString(items.size())) +
           Main.color("yellow", " Items") + " on it.";
  }
}
