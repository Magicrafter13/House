public class Empty implements Item {
  private static final String message = "You have no items/objects selected";
  private static final String typeS = "No Item";

  public Empty() {}
  public Item getSub(int i) {
    return new Book("This item doesn't contain other items.", "(I don't think it should be possible to see this...)", 2018);
  }
  public String type() {
    return typeS;
  }
  public String listInfo(boolean before_not_after) {
    if (before_not_after) return "";
    else return "";
  }
  public String toString() {
    return message;
  }
}
