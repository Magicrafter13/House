import java.util.ArrayList;

public class Bed implements Item {
  public static final String[] types = {"King", "Queen", "Twin", "Single"};
  private boolean adjustable;
  private int bed_type;
  private static final String typeS = "Bed";

  Bed() {
    adjustable = false;
    bed_type = 2;
  }
  Bed(boolean a, int t) {
    adjustable = a;
    bed_type = (t >= 0 && t < types.length ? t : 2);
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return types[bed_type] + " ";
    if (adjustable) return " - Adjustable";
    return "";
  }
  public String toString() {
    return (adjustable ? "Adjustable" : "Non adjustable") + " " + types[bed_type] + " size bed";
  }
}
