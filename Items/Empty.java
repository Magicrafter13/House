public class Empty implements Item {
  private static final String message = "You have no \u001b[33mItems/Objects\u001B[0m selected";
  private static final String typeS = "No Item";

  public Empty() {}
  public Item getSub(int i) {
    return new Book("This " + Main.bright("yellow", "Item") + " doesn't contain other " + Main.color("yellow", "Items") + ".", "(I don't think it should be possible to see this...)", 2018);
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
    if (before_not_after) return "";
    else return "";
  }
  public String toString() {
    return message;
  }
}
