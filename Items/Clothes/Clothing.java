public class Clothing implements Item {
  private static final String typeS = "Clothing";
  private String color = "";

  public Clothing() {
    color = "Black";
  }
  public Clothing(String c) {
    color = c;
  }
  public void setColor(String c) {
    color = c;
  }
  public String getColor() {
    return color;
  }
  public boolean hasItem(Item test) {
    return false;
  }
  public String type() {
    return typeS;
  }
  public String subType() {
    return type();
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return color + " ";
    return " - Generic";
  }
  public String toString() {
    return "This is a Generic piece of clothing, it is " + color;
  }
}
