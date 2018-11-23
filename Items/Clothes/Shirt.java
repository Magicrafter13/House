public class Shirt extends Clothing implements Item {
  private static final String typeS = "Shirt";

  public Shirt() {
    super();
  }
  public Shirt(String c) {
    super(c);
  }
  public String subType() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return getColor() + " ";
    return "";
  }
  public String toString() {
    return "This is a " + getColor() + " " + subType();
  }
}
