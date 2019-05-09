import java.util.ArrayList;

public class Display implements Item {
  private static final String typeS = "Display";
  private boolean is_monitor;
  private ArrayList<Item> connected_to;
  private double size_inch;
  private int roomID;

  public Display() {
    this(false, new ArrayList<Item>(), 20.0, -1);
  }
  public Display(boolean mon, ArrayList<Item> con, double sin, int i) {
    is_monitor = mon;
    connected_to = con;
    size_inch = (sin > 0 ? sin : 20.0);
    roomID = i;
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if (key.equalsIgnoreCase((is_monitor ? "Monitor" : "Display")))
        output += listInfo(true) + typeS + listInfo(false);
    return output;
  }
  public String export(int space) {
    String retStr = "";
    for (int i = 0; i < space; i++) retStr += " ";
    retStr += "new Display(" + (is_monitor ? "true" : "false") + "new ArrayList<Item>(Arrays.asList( " + (connected_to.size() > 0 ? "\n" : " ");
    for (int i = 0; i < connected_to.size(); i++) {
      if (connected_to.get(i) instanceof Container) {
        switch (connected_to.get(i).subType()) {
          case "Bookshelf": retStr += ((Bookshelf)connected_to.get(i)).export(space + 2); break;
          case "Dresser": retStr += ((Dresser)connected_to.get(i)).export(space + 2); break;
          case "Fridge": retStr += ((Fridge)connected_to.get(i)).export(space + 2); break;
          case "Table": retStr += ((Table)connected_to.get(i)).export(space + 2); break;
          default: retStr += ((Container)connected_to.get(i)).export(space + 2); break;
        }
        continue;
      }
      if (connected_to.get(i) instanceof Display) {
        switch (connected_to.get(i).subType()) {
          case "Bookshelf":
            retStr += ((Bookshelf)connected_to.get(i)).export(space + 2);
            break;
          case "Dresser":
            retStr += ((Dresser)connected_to.get(i)).export(space + 2);
            break;
          case "Fridge":
            retStr += ((Fridge)connected_to.get(i)).export(space + 2);
            break;
          case "Table":
            retStr += ((Table)connected_to.get(i)).export(space + 2);
            break;
          default:
            retStr += ((Container)connected_to.get(i)).export(space + 2);
            break;
        }
        continue;
      }
      for (int s = 0; s < space + 2; s++) retStr += " ";
      retStr += connected_to.get(i).export() + "\n";
    }
    if (connected_to.size() > 0)
      for (int i = 0; i < space; i++)
        retStr += " ";
    return retStr + ")), " + Double.toString(size_inch) + ", " + roomID + "),\n";
  }
  public String export() {
    return "new Display(" + (is_monitor ? "true" : "false") + ", new ArrayList<Item>(Arrays.asList( /*Connected devices*/ )), " + Double.toString(size_inch) + ", " + roomID + "),";
  }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) {
    for (Item i : connected_to) if (i == test) return true;
    return false;
  }
  public String connect(Item item) {
    for (Item i : connected_to) if (i == item) return "This " + Main.bright("yellow", "Item") + " is already connected.";
    connected_to.add(item);
    return "\n" + item.listInfo(true) + Main.bright("yellow", item.type()) + item.listInfo(false) + Main.color("blue", " connected") + " to this " + Main.bright("yellow", "Display") + ".\n";
  }
  public String disconnect(int item) {
    if (item >= 0 && item < connected_to.size()) {
      connected_to.remove(item);
      return "\nDevice " + item + Main.color("blue", " disconnected.\n");
    }
    if (connected_to.size() == 0) return Main.bright("yellow", "Display") + " has no devices connected!";
    return Main.bright("yellow", "Display") + " only has " + Main.bright("cyan", Integer.toString(connected_to.size())) + " device" + (connected_to.size() > 1 ? "s" : "") + " connected to it.";
  }
  public String disconnect(Item d) {
    for (int i = 0; i < connected_to.size(); i++) {
      if (connected_to.get(i) == d) {
        connected_to.remove(i);
        return Main.color("yellow", "\nDevice") + ", " + Main.color("blue", "disconnected") + ".\n";
      }
    }
    return "No matching " + Main.color("yellow", "Device") + " found.";
  }
  public int deviceCount() { return connected_to.size(); }
  public Item getDevice(int i) { return connected_to.get(i); }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? size_inch + "\" " + (is_monitor ? "Monitor" : "TV") + " (" : ") - " + connected_to.size() + " devices are connected to it");
  }
  public String toString() {
    String ret_val = size_inch + "\" " + (is_monitor ? "Monitor" : "TV") + " (" + Main.bright("cyan", Integer.toString(connected_to.size())) + " devices):";
    for (int i = 0; i < connected_to.size(); i++)
      ret_val += "\n" + Main.bright("cyan", Integer.toString(i)) + ": " + connected_to.get(i).listInfo(true) + Main.bright("yellow", connected_to.get(i).type()) + connected_to.get(i).listInfo(false);
    return ret_val;
  }
}
