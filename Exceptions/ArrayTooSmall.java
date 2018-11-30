public class ArrayTooSmall extends Exception {
  public ArrayTooSmall(int required, int actual) {
    super("Size of given Array needs to be at least " + required + ", but is only " + actual + ".");
  }
}
