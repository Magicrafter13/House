public interface Item {
  public int getRoom();
  public boolean hasItem(Item test);
  public String type();
  public String subType();
  public String listInfo(boolean before_not_after);
  public String toString();
}
