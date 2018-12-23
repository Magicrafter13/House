import java.util.ArrayList;

public class Bed implements Item {
  public static final String[] types = {"King", "Queen", "Twin", "Single"};
  private boolean adjustable;
  private int bed_type;
  private static final String typeS = "Bed";
  private int roomID;

  Bed() {
    this(false, 2, -1);
  }
  Bed(boolean a, int t, int i) {
    adjustable = a;
    bed_type = (t >= 0 && t < types.length ? t : 2);
    roomID = i;
  }
  public String export() {
    return "new Bed(" + (adjustable ? "true" : "false") + ", " + bed_type + ", " + roomID + "),";
  }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) { return false; }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return types[bed_type] + " ";
    if (adjustable) return " - Adjustable";
    return "";
  }
  public String toString() {
    return (adjustable ? "Adjustable" : "Non adjustable") + " " + types[bed_type] + " size bed";
  }
}
