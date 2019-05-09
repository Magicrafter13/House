import java.util.ArrayList;

public class Printer implements Item {
  private static String typeS = "Printer";
  private boolean can_fax, can_scan, has_color;
  private int roomID;

  public Printer() {
    can_fax = false;
    can_scan = true;
    has_color = true;
    roomID = -1;
  }
  public Printer(boolean canFax, boolean canScan, boolean hasColor, int room) {
    can_fax = canFax;
    can_scan = canScan;
    has_color = hasColor;
    roomID = room;
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if ((can_fax && key.equalsIgnoreCase("Fax")) || (can_scan && key.equalsIgnoreCase("Scan")) || (has_color && key.equalsIgnoreCase("Color")))
        output += listInfo(true) + typeS + listInfo(false);
    return output;
  }
  public int getRoom() {
    return roomID;
  }
  public String type() { return typeS; }
  public String subType() { return typeS; }
  public String export() {
    return "new Printer(" + (can_fax ? "true" : "false") + ", " + (can_scan ? "true" : "false") + ", " + (has_color ? "true" : "false") + "),";
  }
  public boolean hasItem(Item item) { return false; }
  public String listInfo(boolean before_not_after) {
    return before_not_after ? has_color ? "Color " : "Black/White " : can_fax || can_scan ? ", with " + (can_fax ? "Fax" + (can_scan ? ", and Scanner" : "") : "Scanner") : "";
  }
  public String ToString() {
    return Main.bright("yellow", "Printer") + ":\n" + "\tHas Color: " + (has_color ? "Yes" : "No") + "\n\tCan Fax: " + (can_fax ? "Yes" : "No") + "\n\tCan Scan: " + (can_scan ? "Yes" : "No");
  }
}
