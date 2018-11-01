public class Console implements Item {
  private static final String typeS = "Console";
  public static final String[] types = {"Console", "Handheld", "Hybrid System"};
  private int sys_type;
  private String company;
  private String system;

  public Console() {
    sys_type = 0;
    company = "Generi-sys";
    system = "Generic System 1000";
  }
  public Console(int type, String c, String s) {
    this();
    if (type >= 0 && type < types.length) sys_type = type;
    company = c;
    system = s;
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return company + " ";
    else return " - " + types[sys_type];
  }
  public String toString() {
    return "This Video Game " + types[sys_type] + ", is a " + company + "\n" +
           system;
  }
}
