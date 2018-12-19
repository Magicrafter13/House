public class Console implements Item {
  private static final String typeS = "Console";
  public static final String[] types = {"Console", "Handheld", "Hybrid System"};
  private int sys_type;
  private String company;
  private String system;
  private int roomID;

  public Console() {
    this(0, "Generic-sys", "Generic System 1000", -1);
  }
  public Console(int type, String c, String s, int i) {
    if (type >= 0 && type < types.length) sys_type = type;
    company = c;
    system = s;
    roomID = i;
  }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) { return false; }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? "" : " - " + types[sys_type]);
  }
  public String toString() {
    return "This Video Game " + types[sys_type] + ", is a " + company + "\n" +
           system;
  }
}
