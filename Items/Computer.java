public class Computer implements Item {
  private String computer_type;
  private boolean is_on = false;
  private static int total_comps = 0;
  private int id;
  private static final String typeS = "Computer";
  private String brand;
  private String family;
  private String model;

  public Computer() {
    computer_type = "Desktop";
    id = total_comps;
    total_comps++;
    brand = "Generic";
    family = "PC";
    model = "";
  }
  public Computer(String b, String f, String m, boolean state, String type) {
    this();
    computer_type = type;
    is_on = state;
    brand = b;
    family = f;
    model = m;
  }
  public boolean hasItem(Item test) {
    return false;
  }
  public void reset(String b, String f, String m, boolean state, String type) {
    computer_type = type;
    is_on = state;
    brand = b;
    family = f;
    model = m;
  }
  public void turnOn() {
    is_on = true;
  }
  public void turnOff() {
    is_on = false;
  }
  public String type() {
    return typeS;
  }
  public String subType() {
    return type();
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return brand + " ";
    else return ", turned " + (is_on ? "on" : "off");
  }
  public String toString() {
    return computer_type + " " + Main.bright("yellow", "Computer") + ", ID:" + id + "\n" +
           "Currently powered " + (is_on ? "on" : "off") + "\n" +
           "It is a(n) " + brand + " " + family + " " + model;
  }
}
