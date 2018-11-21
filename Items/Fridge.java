import java.util.ArrayList;

public class Fridge extends Container implements Item {
  private static final String typeS = "Fridge";
  private double temperature = 35.0;
  private boolean celsius = false;
  private boolean hasFreezer = false;
  private double freezerTemp = 0.0;
  private boolean freezerCelsius = false;

  public Fridge() {
    super();
  }
  public Fridge(ArrayList<Item> is) {
    super(is);
  }
  public Fridge(boolean f) {
    super();
    hasFreezer = f;
  }
  public Fridge(ArrayList<Item> is, boolean f) {
    super(is);
    hasFreezer = f;
  }
  public void toCelsius(boolean freezer) {
    if (freezer) {
      if (!freezerCelsius) {
        freezerCelsius = true;
        freezerTemp = (freezerTemp - 32.0) * 5.0/9.0;
      }
    } else {
      if (!celsius) {
        celsius = true;
        temperature = (temperature - 32.0) * 5.0/9.0;
      }
    }
  }
  public void toFarenheit(boolean freezer) {
    if (freezer) {
      if (freezerCelsius) {
        freezerCelsius = false;
        freezerTemp = (freezerTemp * 9.0/5.0) + 32.0;
      }
    } else {
      if (celsius) {
        celsius = false;
        temperature = (temperature * 9.0/5.0) + 32.0;
      }
    }
  }
  public void tempInc() {
    temperature += 1.0;
  }
  public void tempDec() {
    temperature -= 1.0;
  }
  public void tempChange(double n) {
    temperature += n;
  }
  public void tempReset() {
    celsius = false;
    temperature = 35.0;
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return temperature + "° ";
    return " - " + (size() > 0 ? Main.bright("cyan", Integer.toString(size())) + Main.bright("yellow", " Items") : Main.color("yellow", "Empty")) + (hasFreezer ? ", with " + freezerTemp + "° Freezer - " : "");
  }
  public String toString() {
    return typeS;
  }
}
