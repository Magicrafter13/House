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
  public Fridge(ArrayList<Item> is, boolean f, int i) {
    super(is, i);
    hasFreezer = f;
  }
  public String search(ArrayList<String> keywords) {
    String output = "";
    for (String key : keywords)
      if ((size() == 0 && key.equalsIgnoreCase("Empty")) || (hasFreezer && key.equalsIgnoreCase("Freezer")))
        output += listInfo(true) + typeS + listInfo(false);
    if (output.equals("")) {
      for (int i = 0; i < size(); i++) {
        String temp = super.getItem(i).search(keywords);
        if (output.equals("") && !temp.equals("")) output = "Fridge:\n";
        if (!temp.equals("")) output += "\t" + i + ": " + temp + "\n";
      }
    }
    return output;
  }
  public String export(int space) {
    String retStr = "";
    for (int i = 0; i < space; i++) retStr += " ";
    retStr += "new Fridge(new ArrayList<Item>(Arrays.asList( " + (super.getItems().size() > 0 ? "\n" : " ");
    for (int i = 0; i < super.getItems().size(); i++) {
      if (super.getItem(i) instanceof Container) {
        switch (super.getItem(i).subType()) {
          case "Bookshelf": retStr += ((Bookshelf)super.getItem(i)).export(space + 2); break;
          case "Dresser": retStr += ((Dresser)super.getItem(i)).export(space + 2); break;
          case "Fridge": retStr += ((Fridge)super.getItem(i)).export(space + 2); break;
          case "Table": retStr += ((Table)super.getItem(i)).export(space + 2); break;
          default: retStr += ((Container)super.getItem(i)).export(space + 2); break;
        }
        continue;
      }
      if (super.getItem(i) instanceof Display) {
        retStr += ((Display)super.getItem(i)).export(space + 2) + "\n";
        continue;
      }
      for (int s = 0; s < space + 2; s++) retStr += " ";
      retStr += super.getItem(i).export() + "\n";
    }
    if (super.getItems().size() > 0)
      for (int i = 0; i < space; i++)
        retStr += " ";
    return retStr + ")), " + (hasFreezer ? "true" : "false") + ", " + super.getRoom() + "),\n";
  }
  public String export() {
    return "new Fridge(new ArrayList<Item>(Arrays.asList( /*Items in Fridge*/ )), " + (hasFreezer ? "true" : "false") + ", " + super.getRoom() + "),";
  }
  /*public Fridge(boolean f, int i) {
    super();
    hasFreezer = f;
  }
  public Fridge(ArrayList<Item> is, boolean f, int i) {
    super(is);
    hasFreezer = f;
  }*/
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
  public void tempInc() { temperature += 1.0; }
  public void tempDec() { temperature -= 1.0; }
  public void tempChange(double n) { temperature += n; }
  public void tempReset() {
    celsius = false;
    temperature = 35.0;
  }
  public String subType() { return typeS; }
  public String listInfo(boolean before_not_after) {
    return (before_not_after ? temperature + "° " : " - " + (size() > 0 ? Main.bright("cyan", Integer.toString(size())) + Main.bright("yellow", " Items") : Main.color("yellow", "Empty")) + (hasFreezer ? ", with " + freezerTemp + "° Freezer - " : ""));
  }
  public String toString() {
    String ret_val = Main.color("yellow", "Items") + " in this " + Main.bright("yellow", "Fridge") + ":";
    for (int i = 0; i < size(); i++) ret_val += "\n\t" + Main.bright("cyan", Integer.toString(i)) + ": " + getItem(i).listInfo(true) + getItem(i).subType() + getItem(i).listInfo(false);
    return ret_val + "\nEnd of " + Main.bright("yellow", "Fridge") + " contents.";
  }
}
