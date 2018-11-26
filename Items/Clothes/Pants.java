public class Pants extends Clothing implements Item {
  private static final String typeS = "Pants";

  public Pants() {
    super();
  }
  public Pants(String c) {
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
    return "These are " + getColor() + " " + subType();
  }
}
