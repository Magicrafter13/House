public class InvalidRange extends RuntimeException {
  public InvalidRange(int min, int max) {
    super("int range invalid (min = " + min + ", max = " + max + ") min must be less than max.");
  }
  public InvalidRange(double min, double max) {
    super("double range invalid (min = " + min + ", max = " + max + ") min must be less than max.");
  }
}
