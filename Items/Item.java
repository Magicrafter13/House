import java.util.ArrayList;

public interface Item {
  public int getRoom();
  public boolean hasItem(Item test);
  public String export();
  public String type();
  public String subType();
  public String search(ArrayList<String> keywords);
  public String listInfo(boolean before_not_after);
  public String toString();
}
