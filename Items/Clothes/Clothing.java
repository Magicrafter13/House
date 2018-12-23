public class Clothing implements Item {
  private static final String typeS = "Clothing";
  private String color = "";
  private int roomID;

  public Clothing() {
    color = "Black";
    roomID = -1;
  }
  public Clothing(String c, int i) {
    color = c;
    roomID = i;
  }
  public String export() {
    return "new Clothing(\"" + color + "\", " + roomID + "),";
  }
  public void setColor(String c) { color = c; }
  public String getColor() { return color; }
  public int getRoom() {
    return roomID;
  }
  public boolean hasItem(Item test) { return false; }
  public String type() { return typeS; }
  public String subType() { return type(); }
  public String listInfo(boolean before_not_after) { return (before_not_after ? color + " " : " - Generic"); }
  public String toString() { return "This is a Generic piece of clothing, it is " + color; }
}
