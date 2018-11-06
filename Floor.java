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
  public boolean removeItem(int i, int si) {
    switch (items.get(i).type()) {
    case "Bookshelf":
      ((Bookshelf)items.get(i)).removeBook(si);
      break;
    case "Display":
      ((Display)items.get(i)).disconnect(si);
      break;
    default: return false;
    }
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
