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
  public Item getItem(int i) {
    return items.get(i);
  }
  public Item getItem(int i, int si) {
    Item ret_val = items.get(i);
    if (items.get(i) instanceof Bookshelf) ret_val = ((Bookshelf)items.get(i)).getBook(si);
    return ret_val;
  }
  public int size() {
    return items.size();
  }
  public String toString() {
    return "This floor has " + items.size() + " items on it.";
  }
}
