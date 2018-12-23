public class Computer implements Item {
  private String computer_type;
  private boolean is_on = false;
  private static int total_comps = 0;
  private int id;
  private static final String typeS = "Computer";
  private String brand;
  private String family;
  private String model;
  private int roomID;

  public void reset(String b, String f, String m, boolean state, String type, int id) {
    computer_type = type;
    is_on = state;
    brand = b;
    family = f;
    model = m;
    roomID = id;
  }
  public Computer() {
    this("Generic", "PC", "", false, "Desktop", -1);
  }
  public Computer(String b, String f, String m, boolean state, String type, int id) {
    id = total_comps;
    total_comps++;
    reset(b, f, m, state, type, id);
  }
  public String export() {
    return "new Computer(\"" + brand + "\", \"" + family + "\", \"" + model + "\", " + (is_on ? "true" : "false") + ", \"" + computer_type + "\", " + roomID + "),";
  }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) { return false; }
  public void turnOn() { is_on = true; }
  public void turnOff() { is_on = false; }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? "" : ", turned " + (is_on ? "on" : "off"));
  }
  public String toString() {
    return computer_type + " " + Main.bright("yellow", "Computer") + ", ID:" + id + "\n" +
           "Currently powered " + (is_on ? "on" : "off") + "\n" +
           "It is a(n) " + brand + " " + family + " " + model;
  }
}
