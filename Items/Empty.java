import java.util.ArrayList;

public class Empty implements Item {
  private static final String message = "You have no \u001b[33mItems/Objects\u001B[0m selected";
  private static final String typeS = "No Item";
  private int roomID = -1;

  public Empty() {}
  public String search(ArrayList<String> keywords) {
    return "";
  }
  public Item getSub(int i) {
    return new Book("This " + Main.bright("yellow", "Item") + " doesn't contain other " + Main.color("yellow", "Items") + ".", "(I don't think it should be possible to see this...)", 2018, -1);
  }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) { return false; }
  public String export() {
    return "new Empty(),";
  }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) { return ""; }
  public String toString() { return message; }
}
