import java.util.ArrayList;

public class Display implements Item {
  private static final String typeS = "Display";
  private boolean is_monitor;
  private ArrayList<Item> connected_to = new ArrayList<Item>();
  private double size_inch;

  public Display() {
    is_monitor = false;
    size_inch = 20.0;
  }
  public Display(boolean mon, ArrayList<Item> con, double sin) {
    is_monitor = mon;
    connected_to = con;
    size_inch = (sin > 0 ? sin : 20.0);
  }
  public String connect(Item item) {
    connected_to.add(item);
    return item.listInfo(true) + Main.bright("yellow", item.type()) + item.listInfo(false) + " connected to this " + Main.bright("yellow", "Display") + ".\n";
  }
  public String disconnect(int item) {
    if (item >= 0 && item < connected_to.size()) {
      connected_to.remove(item);
      return "\nDevice " + item + " removed.\n";
    }
    if (connected_to.size() == 0) return Main.bright("yellow", "Display") + " has no devices connected!";
    return Main.bright("yellow", "Display") + " only has " + Main.bright("cyan", connected_to.size()) + " device" + (connected_to.size() > 1 ? "s" : "") + " connected to it.";
  }
  public int deviceCount() {
    return connected_to.size();
  }
  public Item getDevice(int i) {
    return connected_to.get(i);
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return size_inch + "\" " + (is_monitor ? "Monitor" : "TV") + " (";
    else return ") - " + connected_to.size() + " devices are connected to it";
  }
  public String toString() {
    String ret_val = size_inch + "\" " + (is_monitor ? "Monitor" : "TV") + " (" + connected_to.size() + " devices):";
    for (int i = 0; i < connected_to.size(); i++)
      ret_val += "\n" + i + ": " + connected_to.get(i).listInfo(true) + connected_to.get(i).type() + connected_to.get(i).listInfo(false);
    return ret_val;
  }
}
