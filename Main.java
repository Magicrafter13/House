import java.io.*;
import static java.lang.System.*;

import java.util.*;

class Main {
  private static final int verMajor = 1;
  private static final int verMinor = 12;
  private static final int verFix = 0;
  private static String curVer() {return verMajor + "." + verMinor + "." + verFix;}
  public static final String ANSI = "\u001b[";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String BLACK = "30";
  public static final String RED = "31";
  public static final String GREEN = "32";
  public static final String YELLOW = "33";
  public static final String BLUE = "34";
  public static final String PURPLE = "35";
  public static final String CYAN = "36";
  public static final String WHITE = "37";
  public static final String BRIGHT = "1";
  public static final String CEND = "m"; //CEND = Color End
  public static final String ASEP = ";"; //ASEP = Ansi Seperator
  private static final String os = System.getProperty("os.name");
  public static String bright(String color) {
    switch (color.toLowerCase()) {
    case "red": return "\u001b[31;1m";
    case "yellow": return "\u001b[33;1m";
    case "green": return "\u001b[32;1m";
    case "cyan": return "\u001b[36;1m";
    case "blue": return "\u001b[34;1m";
    case "purple": return "\u001b[35;1m";
    case "white": return "\u001b[37;1m";
    case "black": return "\u001b[30;1m";
    }
    return bright("red") + "[!cB]" + ANSI_RESET;
  }
  public static String bright(String color, String text) {
    return bright(color) + text + ANSI_RESET;
  }
  public static String color(String color) {
    switch (color.toLowerCase()) {
    case "red": return "\u001b[31m";
    case "yellow": return "\u001b[33m";
    case "green": return "\u001b[32m";
    case "cyan": return "\u001b[36m";
    case "blue": return "\u001b[34m";
    case "purple": return "\u001b[35m";
    case "white": return "\u001b[37m";
    case "black": return "\u001b[30m";
    }
    return bright("red") + "[!c]" + ANSI_RESET;
  }
  public static String color(String color, String text) {
    return color(color) + text + ANSI_RESET;
  }
  public static String alternate(String color, String text) {
    String ret_val = "";
    for (int i = 0; i < text.length(); i++) ret_val += (i % 2 == 0 ? bright(color, text.substring(i, i + 1)) : color(color, text.substring(i, i + 1)));
    return ret_val + ANSI_RESET;
  }
  private static String help(String cmd) {
    switch (cmd) {
    case "add":
      return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", "add ") + bright("red", "item ") + bright("green", "[arg]\n\n") +
             "\t" + bright("red", "item") + " - must be a valid type\n" +
             "\t " + bright("green", "arg") + " - causes you to be prompted for the requried info to create a new\n" +
             "\t                " + bright("yellow", "Item") + " of this type (without " + bright("green", "arg") + ", a default " + bright("yellow", "Item") + " is created)\n\n" +
             color("blue") + "Adds" + bright("yellow", " Item ") + "to the current floor\n\n";
    case "attach":
      return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", "attach ") + bright("red", "src dst ") + bright("green", "[-d]\n\n") +
             "\t" + bright("red", "src") + " - " + bright("cyan", "integer") + " of an " + bright("red", "Item") + " on the current floor (when used with " + bright("green", "-d") + ", " + bright("red", "src\n") +
             "\t      must be the " + bright("cyan", "integer") + " of the " + bright("yellow", "Item") + " that is attached)\n" +
             "\t" + bright("red", "dst") + " - " + bright("cyan", "integer") + " of an " + bright("yellow", "Item") + " on the current floor\n" +
             "\t " + bright("green", "-d") + " - " + color("blue", "detaches") + color("red", " source") + " from " + color("red", "destination\n\n") +
             color("blue", "[De/A]ttaches ") + bright("red", "src") + " [from/to] " + bright("red", "dst") + ".\n\n";
    case "clear":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "clear\n\n") +
             color("blue", "Clears") + " the console, and places cursor at home position\n\n";
    case "down":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "down\n\n") +
             "Moves to the next floor " + bright("blue", "down") + ", unless you are at the bottom\n\n";
    case "exit":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "exit\n\n") +
             "Stops the program, and returns to your command line/operating environment\n\n";
    case "grab":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "grab ") + bright("red") + "item\n\n" +
             "\titem" + ANSI_RESET + " - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", "list") + ")\n\n" +
             "Changes the \"Viewer\"'s current " + bright("yellow", "Item\n\n");
    case "help":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "help ") + bright("red") + "[command]\n\n" +
             "\tcommand" + ANSI_RESET + " - a valid " + color("blue", "command\n\n") +
             "Colors:\n" +
             "\t   " + alternate("red", "red") + " - warning -or- argument name (usually an integer)\n" +
             "\t         dark: usually expanded name of a commands argument (to show\n" +
             "\t         meaning)\n" +
             "\t" + alternate("yellow", "yellow") + " - Item\n" +
             "\t         dark: when talking about an Item but not using the exact term\n" +
             "\t         \"Item\" (or the exact name of an Item)\n" +
             "\t " + alternate("green", "green") + " - string argument (type it as it appears [without any brackets])\n" +
             "\t         dark: (no use yet)\n" +
             "\t  " + alternate("cyan", "cyan") + " - an integer for use when a command requires an Item number\n" +
             "\t         dark: Item integer for sub-items (ie: a book in a bookshelf)\n" +
             "\t  " + alternate("blue", "blue") + " - command\n" +
             "\t         dark: when referencing the command without using its exact name\n\n";
    case "info":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "info\n\n") +
             "Returns " + bright("blue", "info") + " about the current 'Viewer'\n\n";
    case "list":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "list ") + "[" + bright("red", "item") + "] [(" + bright("green", "-h") + " / " + bright("green", "-f") + ")] [" + bright("green", "-r ") + bright("red", "start end") + "] [" + bright("green", " -p") + "] [" + bright("green", "-i ") + bright("yellow", "Item") + "]\n\n" +
             "\t   " + bright("red", "item") + " - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", "list") + ")\n" +
             "\t" + bright("green", "-h / -f") + " - will show the the \"Viewer\"'s current " + bright("yellow", "Item\n") +
             "\t          Long version is " + bright("green", "--hand") + " or " + bright("green") + "--focus\n" +
             "\t     -r" + ANSI_RESET + " - will " + bright("blue", "list") + " " + color("yellow", "Items") + " between [" + bright("red", "start") + "] and [" + bright("red", "end") + "]\n" +
             "\t          Long version is " + bright("green") + "--range\n" +
             "\t     -p" + ANSI_RESET + " - " + color("blue", "lists") + " all " + color("yellow", "Items") + " on the floor one page at a time (page is\n" +
             "\t          defined as 20 lines)\n" +
             "\t          Long version is " + bright("green") + "--page\n" +
             "\t-i " + bright("yellow", "Item") + " - " + color("blue", "lists") + " all " + color("yellow", "Items") + " of type " + bright("yellow", "Item") + " (" + bright("yellow", "Item") + " string)\n" +
             "\t          Long version is " + bright("green", "--item\n\n") +
             "Used for getting info about an " + bright("yellow", "Item") + ", or multiple " + color("yellow", "Items") + ".\n\n";
    case "move":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "move ") + bright("red") + "item floor\n\n" +
             "\t item" + ANSI_RESET + " - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", "list") + ")\n" +
             "\t" + bright("red", "floor") + " - " + bright("cyan", "integer") + " of floor (or: " + bright("green", "<") + " for next floor down or " + bright("green", ">") + " for next floor\n" +
             "\t        up)\n\n" +
             color("blue", "Moves") + " an " + bright("yellow", "Item") + " from your current floor to the specified floor.\n\n";
    case "remove":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "remove ") + bright("red") + "item\n\n" +
             "\titem" + ANSI_RESET + " - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", "list") + ")\n\n" +
             color("blue", "Removes") + " specified " + bright("yellow", "Item") + " from current floor.\n\n";
    case "up":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "up\n\n") +
             "Moves to the next floor " + bright("blue", "up") + ", unless you are at the top\n\n";
    case "ver":
      return bright("purple", "\nSyntax") + " is: " + bright("blue", "vern\n") +
             "Tells you the current " + bright("blue", "version") + " of the Heck Command Interpretter\n\n";
    default:
      return bright("red") + "Code error!!! (Please report, as this message shouldn't be possible to see.)";
    }
  }
  private static boolean equalsIgnoreCaseOr(String test, String[] strs) {
    for (int i = 0; i < strs.length; i++) if (test.equalsIgnoreCase(strs[i])) return true;
    return false;
  }
  private static boolean matchesAnd(String[] strs, String match) {
    for (int i = 0; i < strs.length; i++) if (!strs[i].matches(match)) return false;
    return true;
  }

  public static void main (String str[]) throws IOException {
    Scanner scan = new Scanner(System.in);

    String command;
    String[] cmds = {""};

    House my_house = new House(2, 2);
    Viewer user = new Viewer(my_house);
    Boolean here = true;

    //This is to keep the contents of my actual house a little more private.
    //Just make your own .java file that returns Items.
    for (int i = 0; i < ItemImport.bookshelfs.length; i++)
      my_house.addItem(ItemImport.bookshelfs_f[i], ItemImport.bookshelfs[i]);
    for (int i = 0; i < ItemImport.computers.length; i++)
      my_house.addItem(ItemImport.computers_f[i], ItemImport.computers[i]);
    for (int i = 0; i < ItemImport.consoles.length; i++)
      my_house.addItem(ItemImport.consoles_f[i], ItemImport.consoles[i]);
    for (int i = 0; i < ItemImport.displays.length; i++)
      my_house.addItem(ItemImport.displays_f[i], ItemImport.displays[i]);
    for (int i = 0; i < ItemImport.beds.length; i++)
      my_house.addItem(ItemImport.beds_f[i], ItemImport.beds[i]);

    while (here) {
      System.out.print("> ");
      command = scan.nextLine();
      String[] temp_arr = command.split(" +");
      cmds = new String[temp_arr.length];
      cmds = temp_arr.clone();
      if (cmds.length > 0) {
        switch (cmds[0].toLowerCase()) {
        case "attach":
          if (cmds.length > 1) {
            if (cmds[1].matches("-?[0-9]+")) {
              if (cmds.length > 2) {
                if (cmds[2].matches("-?[0-9]+")) {
                  int src = Math.abs(Integer.parseInt(cmds[1]));
                  int dst = Math.abs(Integer.parseInt(cmds[2]));
                  if (cmds.length > 3) {
                    if (cmds[3].equalsIgnoreCase("-d")) {
                      Item dst_i = user.getItem(dst);
                      switch (dst_i.type()) {
                      case "Bookshelf":
                        if (src < ((Bookshelf)dst_i).bookCount()) user.addItem(((Bookshelf)dst_i).getBook(src));
                        System.out.println(((Bookshelf)dst_i).removeBook(src));
                        break;
                      case "Display":
                        System.out.println(((Display)dst_i).disconnect(src));
                        break;
                      default:
                        System.out.print("That " + bright("yellow", "Item") + " cannot have things " + color("blue", "detached") + " from it.\n");
                        break;
                      }
                    } else System.out.print("Invalid argument, did you mean " + bright("green", "-d") + "?\n");
                  } else if (user.isItem(src) && user.isItem(dst)) {
                    Item src_i = user.getItem(src);
                    Item dst_i = user.getItem(dst);
                    switch (dst_i.type()) {
                    case "Bookshelf":
                      if (src_i instanceof Book) {
                        user.removeItem(src);
                        ((Bookshelf)dst_i).addBook((Book)src_i);
                      } else System.out.print(bright("yellow", "Item ") + src + " is not a " + bright("yellow", "Book") + ".\n");
                      break;
                    case "Display":
                      if (src_i instanceof Computer || src_i instanceof Console) System.out.println("\n" + ((Display)dst_i).connect(src_i));
                      else System.out.print(bright("yellow", "Item ") + src + " cannot connect to a " + bright("yellow", "Display") + ".\n");
                      break;
                    default:
                      System.out.print("Item cannot have things attached to it.\n");
                      break;
                    }
                  } else System.out.print("The floor only has " + bright("cyan", Integer.toString(user.floorSize())) + " items.\n");
                } else System.out.print(color("red", "Item") + " must be an " + bright("cyan", "integer") + ".\n");
              } else System.out.print(bright("blue", "\nAttach") + " it to what?\n\n");
            } else System.out.print(color("red", "Item") + " must be an " + bright("cyan", "integer") + ".\n");
          } else System.out.print(bright("blue", "\nAttach") + " what to what?\n\n");
          break;
        case "move":
          if (cmds.length > 1) {
            if (cmds[1].matches("-?[0-9]+")) {
              if (cmds.length > 2) {
                if (cmds[2].matches("-?[0-9]+") || cmds[2].equals("<") || cmds[2].equals(">")) {
                  Item old_item = user.cur_item;
                  int item = Math.abs(Integer.parseInt(cmds[1]));
                  int destination = (cmds[2].matches("-?[0-9]+") ? Math.abs(Integer.parseInt(cmds[2])) : (cmds[2].equalsIgnoreCase("<") ? user.curFloor() - 1 : user.curFloor() + 1));
                  int old_floor = user.curFloor();
                  if(user.changeItemFocus(item)) {
                    if (user.goFloor(destination)) {
                      user.addItem(user.cur_item);
                      user.goFloor(old_floor);
                      user.removeItem(item);
                      System.out.print("\nThis " + bright("yellow", "Item ") + bright("blue", "moved") + " to floor " + destination + "\n" + user.cur_item + "\n\n");
                    } else System.out.print("Floor does not exist.\n");
                  } else System.out.print(bright("yellow", "Item") + " does not exist.\n");
                  user.cur_item = old_item;
                } else System.out.print("Floor must be an " + bright("cyan", "integer") + ", or: " + bright("green", "<") + " or " + bright("green", ">") + ".\n");
              } else System.out.print(bright("blue", "\nMove") + " it where?\n\n");
            } else System.out.print(bright("yellow", "Item") + " must be an " + bright("cyan", "integer") + ".\n");
          } else System.out.print(bright("blue", "\nMove") + " what, and where?\n\n");
          break;
        case "grab":
        case "select":
          if (cmds.length > 1) {
            if (cmds[1].matches("-?[0-9]+")) {
              if (cmds.length > 2 ) {
                if (cmds[2].matches("-?[0-9]+")) {
                  switch(user.changeItemFocus(Math.abs(Integer.parseInt(cmds[1])), Math.abs(Integer.parseInt(cmds[2])))) {
                  case 0: System.out.print("\nThis " + bright("yellow", "Item") + " selected: (of type " + bright("yellow", user.cur_item.type()) + ")\n\n" + user.cur_item + "\n\n"); break;
                  case 1: System.out.print("Either " + bright("yellow", "Item ") + bright("cyan", cmds[1]) + " doesn't have any " + bright("yellow", "sub-Items") + ", or the " + bright("cyan", "integer") + " you entered is too high.\n"); break;
                  case 2: System.out.print("\"" + cmds[1] + "\" is invalid, must be less than the floor " + bright("yellow", "Item") + " size of: " + bright("cyan", Integer.toString(user.floorSize())) + "\n"); break;
                  default: System.out.print(bright("red", "ERROR: get sub-item did not return 0, 1, or 2. Please report this!\n"));
                  }
                } else System.out.print("\"" + cmds[2] + "\" is not a valid " + bright("cyan", "integer\n"));
              } else if (user.changeItemFocus(Math.abs(Integer.parseInt(cmds[1])))) System.out.print("\nThis " + bright("yellow", "Item") + " selected: (of type " + bright("yellow", user.cur_item.type()) + ")\n\n" + user.cur_item + "\n\n");
              else System.out.print("\"" + cmds[1] + "\" is invalid, must be less than the floor " + bright("yellow", "Item") + " size of: " + bright("cyan", Integer.toString(user.floorSize())) + "\n");
            } else System.out.print("\"" + cmds[1] + "\" is not a valid " + bright("cyan", "integer\n"));
          } else System.out.print(bright("blue", "\nGrab") + " what?\n\n");
          break;
        case "remove":
          if (cmds.length > 1) {
            if (cmds[1].matches("-?[0-9]+")) {
              Item temp_item = user.cur_item;
              Boolean valid_answer = false;
              if (cmds.length > 2) {
                if (cmds[2].matches("-?[0-9]+")) {
                  switch (user.changeItemFocus(Math.abs(Integer.parseInt(cmds[1])), Math.abs(Integer.parseInt(cmds[2])))) {
                  case 0:
                    while (!valid_answer) {
                      System.out.print("\nThis " + bright("yellow", "Item") + " is:\n" + user.cur_item + "\n\n" +
                                       bright("red") + "Are you sure you want to delete this? [Y/N] > ");
                      user.cur_item = temp_item;
                      String yenu = scan.nextLine().toUpperCase();
                      System.out.println(ANSI_RESET);
                      switch (yenu) {
                      case "Y": user.removeItem(Math.abs(Integer.parseInt(cmds[1])), Math.abs(Integer.parseInt(cmds[2])));
                      case "N": valid_answer = true;
                      }
                    }
                    break;
                  case 1: System.out.print("This " + bright("yellow", "Item") + " either has no " + color("yellow", "sub-Items") + " on it, or the " + bright("cyan", "integer") + " is too high\n"); break;
                  case 2: System.out.print("This floor only has " + bright("cyan", Integer.toString(user.floorSize())) + " items on it\n"); break;
                  }
                } else System.out.print("\"" + cmds[2] + "\" is not a valid integer\n");
              } else if (user.changeItemFocus(Math.abs(Integer.parseInt(cmds[1])))) {
                while (!valid_answer) {
                  System.out.print("\nThis " + bright("yellow", "Item") + " is:\n" + user.cur_item + "\n\n" +
                                   bright("red") + "Are you sure you want to delete this? [Y/N] > ");
                  user.cur_item = temp_item;
                  String yenu = scan.nextLine().toUpperCase();
                  System.out.println(ANSI_RESET);
                  switch (yenu) {
                  case "Y": user.removeItem(Math.abs(Integer.parseInt(cmds[1])));
                  case "N": valid_answer = true;
                  }
                }
              } else System.out.print("This floor only has " + bright("cyan", Integer.toString(user.floorSize())) + " items on it\n");
            } else System.out.print("\"" + cmds[1] + "\" is not a valid integer\n");
          } else System.out.print("\nRemove what?\n\n");
          break;
        case "list":
        case "look":
          if (cmds.length > 1) {
            if (equalsIgnoreCaseOr(cmds[1], new String[]{"--hand", "--focus", "-h", "-f"})) System.out.print("\n" + user.viewCurItem() + "\n\n");
            else if (equalsIgnoreCaseOr(cmds[1], new String[]{"-i", "--item"})) {
              if (cmds.length > 2) System.out.println(user.list(cmds[2]));
              else System.out.print("No " + bright("red", "Item") + " type specified.\n");
            } else if (equalsIgnoreCaseOr(cmds[1], new String[]{"-p", "--page"})) {
              for (int i = 0; i < (user.floorSize() / 20 + (user.floorSize() % 20 == 0 ? 0 : 1)); i++) {
                System.out.println("\n\tFloor " + color("blue", "Listing") + " - Page " + (i + 1));
                boolean end_test = (20 * (i + 1) < user.floorSize());
                System.out.println(user.list(20 * i, (end_test ? 20 * (i + 1) : user.floorSize())));
                if (end_test) {
                  System.out.print("Press enter to continue > ");
                  scan.nextLine();
                }
              }
            } else if (equalsIgnoreCaseOr(cmds[1], new String[]{"-r", "--range"})) {
              if (cmds.length > 3 && matchesAnd(new String[]{cmds[2], cmds[3]}, "-?[0-9]+")) System.out.println(user.list(Integer.parseInt(cmds[2]), Integer.parseInt(cmds[3]) + 1));
              else System.out.print(bright("blue", "range") + " requires " + bright("cyan", "2 integers\n"));
            } else if (cmds[1].matches("-?[0-9]+")) {
              if (Integer.parseInt(cmds[1]) < user.floorSize()) {
                Item temp_item = user.cur_item;
                user.changeItemFocus(Integer.parseInt(cmds[1]));
                switch (user.cur_item.type()) {
                case "Bookshelf":
                  System.out.print("This " + bright("yellow", "Item") + " is a " + bright("yellow", "Bookshelf") + ", would you like to see:\n" +
                                   "(Y) A specific " + bright("yellow", "Book") + "\n(N) Just the " + bright("yellow", "Bookshelf") + "\n\n");
                  while (true) {
                    System.out.print("[Y/N] > ");
                    String temp = scan.nextLine().toUpperCase();
                    int b_c = ((Bookshelf)user.cur_item).bookCount();
                    if (temp.equals("Y") && b_c > 0) {
                      System.out.print("\nWhich " + bright("yellow", "Book:\n\n"));
                      while (true) {
                        System.out.print("[" + bright("cyan", "0") + "-" + bright("cyan", Integer.toString((b_c - 1))) + "] > ");
                        int bk = Math.abs(scan.nextInt());
                        scan.nextLine();
                        if (bk < b_c) {
                          System.out.print("\n" + ((Bookshelf)user.cur_item).getBook(bk));
                          break;
                        }
                      }
                    }
                    if (temp.equals("N") || ((Bookshelf)user.cur_item).bookCount() == 0) System.out.print("\n" + user.viewCurItem());
                    System.out.println();
                    if (equalsIgnoreCaseOr(temp, new String[]{"Y", "N"})) break;
                  }
                  System.out.println();
                  break;
                case "Display":
                  System.out.print("This " + bright("yellow", "Item") + " is a " + bright("yellow", "Display") + ", would you like to see:\n" +
                                   "(Y) A specific device\n(N) Just the " + bright("yellow", "Display\n\n"));
                  while (true) {
                    System.out.print("[Y/N] > ");
                    String temp = scan.nextLine().toUpperCase();
                    if (temp.equals("Y") && ((Display)user.cur_item).deviceCount() > 0) {
                      System.out.print("\nWhich device:\n\n");
                      while (true) {
                        System.out.print("[" + bright("cyan", "0") + "-" + bright("cyan", Integer.toString((((Display)user.cur_item).deviceCount() - 1))) + "] > ");
                        int dv = Math.abs(scan.nextInt());
                        scan.nextLine();
                        if (dv < ((Display)user.cur_item).deviceCount()) {
                          System.out.print("\n" + ((Display)user.cur_item).getDevice(dv));
                          break;
                        }
                      }
                    }
                    if (temp.equals("N") || ((Display)user.cur_item).deviceCount() == 0) System.out.print("\n" + user.viewCurItem());
                    System.out.println();
                    if (equalsIgnoreCaseOr(temp, new String[]{"Y", "N"})) break;
                  }
                  System.out.println();
                  break;
                case "Book":
                case "Computer":
                case "Console":
                case "Bed":
                  System.out.print("\n" + user.viewCurItem() + "\n\n");
                  break;
                }
                user.cur_item = temp_item;
              } else System.out.print("This floor only has " + user.floorSize() + color("yellow", " Items") + " on it\n");
            } else System.out.print("\"" + cmds[1] + "\" is not a valid " + bright("cyan", "integer\n"));
          } else System.out.println(user.list());
          break;
        case "add":
          if (cmds.length > 1) {
            switch (cmds[1].toLowerCase()) {
              case "bookshelf":
                Bookshelf temp_shelf = new Bookshelf();
                if (cmds.length > 2) {
                  if (cmds[2].equalsIgnoreCase("arg")) {
                    System.out.print("\nHow many " + color("yellow", "books") + " will be on this " + color("yellow", "shelf") + "? > ");
                    int length = scan.nextInt();
                    scan.nextLine();
                    System.out.println();
                    for (int i = 0; i < length; i++) {
                      System.out.print(bright("yellow", "Book ") + bright("cyan", Integer.toString(i)) + "\n");
                      System.out.print("\nEnter " + bright("yellow", "Book") + " Title > ");
                      String title = scan.nextLine();
                      System.out.print("\nEnter " + bright("yellow", "Book") + " Author > ");
                      String author = scan.nextLine();
                      System.out.print("\nEnter Publishing Year > ");
                      int year = scan.nextInt();
                      scan.nextLine();
                      System.out.println();
                      temp_shelf.addBook(new Book(title, author, year));
                    }
                    System.out.print("\nThis " + bright("yellow", "Bookshelf") + " created:\n" + temp_shelf + "\n\n");
                  } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                } else System.out.print("\nNew " + bright("yellow", "Bookshelf") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                user.addItem(temp_shelf);
                break;
              case "book":
                Book temp_book = new Book();
                if (cmds.length > 2) {
                  if (cmds[2].equalsIgnoreCase("arg")) {
                    System.out.print("\nEnter " + bright("yellow", "Book") + " Title > ");
                    String title = scan.nextLine();
                    System.out.print("\nEnter " + bright("yellow", "Book") + " Author > ");
                    String author = scan.nextLine();
                    System.out.print("\nEnter Publishing Year > ");
                    int year = scan.nextInt();
                    scan.nextLine();
                    temp_book.reset(title, author, year);
                    System.out.print("\nThis " + bright("yellow", "Book") + " added:\n" + temp_book + "\n\n");
                  } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                } else System.out.print("\nNew " + bright("yellow", "Book") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                user.addItem(temp_book);
                break;
              case "computer":
                Computer temp_comp = new Computer();
                if (cmds.length > 2) {
                  if (cmds[2].equalsIgnoreCase("arg")) {
                    System.out.print("\nWhat kind of " + bright("yellow", "Computer") + " is it? (Desktop, Laptop, etc) > ");
                    String type = scan.nextLine();
                    System.out.print("\n" + bright("yellow", "Computer") + " Brand (ie: HP, Microsoft) > ");
                    String brand = scan.nextLine();
                    System.out.print("" + bright("yellow", "Computer") + " Family (ie: Pavilion, Surface) > ");
                    String family = scan.nextLine();
                    System.out.print("" + bright("yellow", "Computer") + " Model (ie: dv6, Pro 3) > ");
                    String model = scan.nextLine();
                    System.out.print("\nIs it on? (Invalid input will default to no)\n Yes or no? [Y/N] > ");
                    String is_on = scan.nextLine().toUpperCase();
                    temp_comp.reset(brand, family, model, (is_on.equals("Y") ? true : false), type);
                    System.out.print("\nThis " + bright("yellow", "Computer") + " added:\n" + temp_comp + "\n\n");
                  } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                } else System.out.print("\nNew " + bright("yellow", "Computer") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                user.addItem(temp_comp);
                break;
              case "console":
                Console temp_console = new Console();
                if (cmds.length > 2) {
                  if (cmds[2].equalsIgnoreCase("arg")) {
                    for (int i = 0; i < Console.types.length; i++) System.out.print(bright("cyan", Integer.toString(i)) + ": " + Console.types[i] + " ");
                    System.out.println();
                    System.out.print("\nEnter " + bright("yellow", "Console") + " Type > ");
                    int temp_type = scan.nextInt();
                    scan.nextLine();
                    System.out.print("\nEnter " + bright("yellow", "Console") + " Manufacturer (ie Nintendo) > ");
                    String com = scan.nextLine();
                    System.out.print("\nEnter " + bright("yellow", "Console") + " Name (ie GameCube) > ");
                    String sys = scan.nextLine();
                    temp_console = new Console(temp_type, com, sys);
                    System.out.print("\nThis " + bright("yellow", "Console") + " added:\n" + temp_console + "\n\n");
                  } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                } else System.out.print("\nNew " + bright("yellow", "Console") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                user.addItem(temp_console);
                break;
              case "display":
                Display temp_disp = new Display();
                if (cmds.length > 2) {
                  if (cmds[2].equalsIgnoreCase("arg")) {
                    System.out.print("\nIs it a Monitor (Y) or a TV (N)?\nWill default to (Y)es if next input is invalid.\n[Y/N] > ");
                    String is_mon = scan.nextLine().toUpperCase();
                    System.out.print("\nType the number for each device connected to this " + bright("yellow", "Display") + " seperated by a space.\n(Optional)\n> ");
                    String[] con_devs = scan.nextLine().split(" +");
                    ArrayList<Item> valid_devs = new ArrayList<Item>();
                    ArrayList<Integer> added = new ArrayList<Integer>();
                    ArrayList<Integer> not_added = new ArrayList<Integer>();
                    ArrayList<String> not_number = new ArrayList<String>();
                    for (String dev : con_devs) {
                      if (dev.matches("-?[0-9]+")) {
                        int devID = Integer.parseInt(dev);
                        if (devID >= 0 && devID < user.floorSize()) added.add(devID);
                        else not_added.add(devID);
                      } else not_number.add(dev);
                    }
                    System.out.print("\nAdded: ");
                    for (int num : added) System.out.print(num + " ");
                    System.out.print("\n\nNot added: ");
                    for (int num : not_added) System.out.print(num + " ");
                    System.out.print("\n\nNot a number: ");
                    for (String str_num : not_number) System.out.print(str_num + " ");
                    System.out.print("\n\nEnter the " + color("yellow", "Display's") + " size in inches (decimals allowed) > ");
                    double size = scan.nextDouble();
                    scan.nextLine();
                    ArrayList<Item> new_items = new ArrayList<Item>();
                    for (int id : added) new_items.add(user.getItem(id));
                    temp_disp = new Display((is_mon.equals("N") ? false : true), new_items, size);
                    System.out.print("\nThis " + bright("yellow", "Display") + " added:\n" + temp_disp + "\n\n");
                  } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                } else System.out.print("\nNew " + bright("yellow", "Display") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                user.addItem(temp_disp);
                break;
              case "bed":
                Bed temp_bed = new Bed();
                if (cmds.length > 2) {
                  if (cmds[2].equalsIgnoreCase("arg")) {
                    System.out.print("\nIs this " + bright("yellow", "Bed") + " adjustable? (Invalid input will default to N)\n[Y/N] > ");
                    boolean can_move = scan.nextLine().equalsIgnoreCase("Y");
                    System.out.println();
                    for (int i = 0; i < Bed.types.length; i++) System.out.print("[" + bright("cyan", Integer.toString(i)) + "] " + Bed.types[i] + " ");
                    System.out.print("\nInvalid input defaults to " + bright("cyan", "2"));
                    System.out.print("\n[" + bright("cyan", "0") + "-" + bright("cyan", Integer.toString((Bed.types.length - 1))) + "] > ");
                    String type_input = scan.nextLine();
                    int bed_type = (type_input.matches("-?[0-9]+") && Math.abs(Integer.parseInt(type_input)) < Bed.types.length ? Math.abs(Integer.parseInt(type_input)) : 2);
                    temp_bed = new Bed(can_move, bed_type);
                    System.out.print("\nThis " + bright("yellow", "Bed") + " added:\n" + temp_bed + "\n\n");
                  } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                } else System.out.print("\nNew " + bright("yellow", "Bed") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                user.addItem(temp_bed);
                break;
              default:
                System.out.print("\"" + cmds[1] + "\" is not a valid " + bright("yellow", "Item") + " type:\n");
                for (int i = 0; i < cmds.length; i++) System.out.print(cmds[i] + " ");
                System.out.print("\n" + help("add"));
                break;
            }
          } else System.out.print("\nInvalid syntax, requires at least one argument\n\n");
          break;
        case "status":
        case "info":
          System.out.println("\n" + user + "\n");
          break;
        case ">":
        case "up":
          System.out.println(user.goUp());
          break;
        case "<":
        case "down":
          System.out.println(user.goDown());
          break;
        case "": break;
        case "help":
          if (cmds.length > 1) {
            switch (cmds[1].toLowerCase()) {
            case "add": System.out.print(help("add")); break;
            case "attach": System.out.print(help("attach")); break;
            case "clear": case "cls": System.out.print(help("clear")); break;
            case "down": System.out.print(help("down")); break;
            case "exit": case "quit": System.out.print(help("exit")); break;
            case "grab": case "select": System.out.print(help("grab")); break;
            case "help": System.out.print(help("help")); break;
            case "info": case "status": System.out.print(help("info")); break;
            case "list": case "look": System.out.print(help("list")); break;
            case "move": System.out.print(help("move")); break;
            case "remove": System.out.print(help("remove")); break;
            case "up": System.out.print(help("up")); break;
            case "ver": case "version": System.out.print(help("ver")); break;
            default: System.out.print("\nNo help was found for this command.\n\n"); break;
            }
          } else {
            System.out.println();
            System.out.print("          " + bright("blue") + "add" + ANSI_RESET + " - adds an " + bright("yellow") + "Item" + ANSI_RESET + " to the current floor\n");
            System.out.print("       " + bright("blue") + "attach" + ANSI_RESET + " - attaches (or detaches) one " + bright("yellow") + "Item" + ANSI_RESET + " to (from) another\n");
            System.out.print("  " + bright("blue") + "clear / cls" + ANSI_RESET + " - clears the screen\n");
            System.out.print("         " + bright("blue") + "down" + ANSI_RESET + " - goes down 1 floor\n");
            System.out.print("  " + bright("blue") + "exit / quit" + ANSI_RESET + " - stops the program\n");
            System.out.print("" + bright("blue") + "grab / select" + ANSI_RESET + " - sets which " + bright("yellow") + "Item" + ANSI_RESET + " you are currently focused on\n");
            System.out.print("         " + bright("blue") + "help" + ANSI_RESET + " - displays this screen, and others\n");
            System.out.print("" + bright("blue") + "info / status" + ANSI_RESET + " - shows information about you and the " + bright("yellow") + "House" + ANSI_RESET + " you are currently in\n");
            System.out.print("  " + bright("blue") + "list / look" + ANSI_RESET + " - shows the " + bright("yellow") + "Item" + ANSI_RESET + "s on the current floor, or shows info about a\n" +
                             "                specific " + bright("yellow") + "Item\n");
            System.out.print("         " + bright("blue") + "move" + ANSI_RESET + " - moves an " + bright("yellow") + "Item" + ANSI_RESET + " to another floor\n");
            System.out.print("       " + bright("blue") + "remove" + ANSI_RESET + " - removes an " + bright("yellow") + "Item" + ANSI_RESET + " from the current floor\n");
            System.out.print("           " + bright("blue") + "up" + ANSI_RESET + " - goes up 1 floor\n");
            System.out.print("" + bright("blue") + "ver / version" + ANSI_RESET + " - displays information about this command interpretter\n");
            System.out.print("\ntype help (command) for more detailed information about a specific command\n\n");
          }
          break;
        case "clear":
        case "cls":
          System.out.print("Running on: " + os + "\n");
          System.out.print("\033[H\033[2J");
          //if (os.contains("Windows")) Runtime.getRuntime().exec("cls");
          //else Runtime.getRuntime().exec("clear");
          break;
        case "exit":
        case "quit":
          here = false;
          break;
        case "ver":
        case "version":
          System.out.print("\n" + bright("red", "H") + bright("green", "e") + bright("blue", "c") + "k Command Interpretter\n\tVersion " + curVer() + "\n\n");
          break;
        default:
          System.out.print("\"" + cmds[0] + "\" is not a valid command:\n");
          for (int i = 0; i < cmds.length; i++) System.out.print(cmds[i] + " ");
          System.out.print("\n");
          break;
        }
      }
    }
		scan.close();
  }
}
