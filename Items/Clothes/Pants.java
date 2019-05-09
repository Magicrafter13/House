import java.util.ArrayList;

public class Pants extends Clothing implements Item {
  private static final String typeS = "Pants";

  public Pants() {
    super();
  }
  public Pants(String c, int i) {
    super(c, i);
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if (key.equalsIgnoreCase(getColor()))
        output += listInfo(true) + typeS + listInfo(false);
    return output;
  }
  public String export() {
    return "new Pants(\"" + super.getColor() + "\", " + super.getRoom() + "),";
  }
  public String subType() { return typeS; }
  public String listInfo(boolean before_not_after) { return (before_not_after ? getColor() + " " : ""); }
  public String toString() { return "These are " + getColor() + " " + subType(); }
}
