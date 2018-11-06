import java.util.ArrayList;

public class Floor {
  private ArrayList<Item> items = new ArrayList<Item>();

  public Floor() {
    items = new ArrayList<Item>();
    items.clear();
  }
  public Floor(ArrayList<Item> i) {
    this();
    items = i;
  }
  public void addItem(Item i) {
    items.add(i);
  }
  public void removeItem(int i) {
    items.remove(i);
  }
  public void removeItem(Item test) {
    for (int i = 0; i < items.size(); i++) if (items.get(i) == test) items.remove(i);
  }
  public boolean removeItem(int in, int sin) {
    int remove_from_floor = -1;
    for (int i = 0; i < items.size(); i++) {
      switch(items.get(in).type()) {
      case "Bookshelf":
        if (((Bookshelf)items.get(in)).getBook(sin) == items.get(i)) remove_from_floor = i;
        break;
      case "Display":
        if (((Display)items.get(in)).getDevice(sin) == items.get(i)) remove_from_floor = i;
        break;
      }
    }
    switch (items.get(in).type()) {
    case "Bookshelf":
      ((Bookshelf)items.get(in)).removeBook(sin);
      break;
    case "Display":
      ((Display)items.get(in)).disconnect(sin);
      break;
    default: return false;
    }
    if (remove_from_floor > -1) items.remove(remove_from_floor);
    return true;
  }
  public ArrayList<Item> getItems() {
    return items;
  }
  public Item getItem(int i) {
    if (i >= 0 && i < items.size()) return items.get(i);
    return new Empty();
  }
  public Item getItem(int i, int si) {
    Item ret_val = new Empty();
    if (i >= 0 && i < items.size()) {
      if (items.get(i) instanceof Bookshelf) ret_val = ((Bookshelf)items.get(i)).getBook(si);
      if (items.get(i) instanceof Display) ret_val = ((Display)items.get(i)).getDevice(si);
    }
    return ret_val;
  }
  public int size() {
    return items.size();
  }
  public String toString() {
    return "This floor has " + items.size() + " items on it.";
  }
}
