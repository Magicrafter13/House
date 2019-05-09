import java.io.*;
import java.io.File;
import java.io.FileWriter;
import static java.lang.System.*;

import java.util.*;

class Main {
  private static final int verMajor = 2;
  private static final int verMinor = 6;
  private static final int verFix = 2;
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
  public static final String[] top_types = {"Bed", "Book", "Computer", "Console", "Display", "Clothing", "Container", "Printer"};
  public static final String[] floor_interacts = {"light (or lights)"};
  private static String help(String cmd) {
    String ret_val = "";
    switch (cmd) {
      case "add":
        return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", "add ") + bright("red", "item ") + bright("green", "[arg]\n\n") +
               "\t" + bright("red", "item") + " - must be an Item type, or House\n" +
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
      case "clear": case "cls":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", cmd.toLowerCase()) + "\n\n" +
               color("blue", "Clears") + " the console, and places cursor at home position\n\n";
      case "down":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", "down ") + "[" + bright("red", "floors") + "]\n\n" +
               "\t" + bright("red", "floors") + " - " + bright("cyan", "integer") + " of how many floors you want to go down\n\n" +
               "Moves to the next floor " + bright("blue", "down") + ", unless you are at the bottom\n\n";
      case "exit": case "quit":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", cmd.toLowerCase()) + "\n\n" +
               "Stops the program, and returns to your command line/operating environment\n\n";
      case "goto":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", "goto") + " [" + bright("red", "room") + "]\n\n" +
               bright("red", "\troom") + " - " + bright("cyan", "integer") + " of room (run " + bright("blue", "goto") + " without arguments to see rooms) [" + bright("green", "-1\n") +
               "\t       exits all rooms (think of it like a hallway)]\n\n" +
               "Changes the current room of the \"Viewer\"\n\n";
      case "grab": case "select":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", cmd.toLowerCase()) + bright("red") + " item\n\n" +
               "\titem" + ANSI_RESET + " - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", "list") + ")\n\n" +
               "Changes the \"Viewer\"'s current " + bright("yellow", "Item\n\n");
      case "help":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", "help ") + "[" + bright("red", "command") + " [" + bright("red", "sub-topic") + "] ]\n\n" +
               "\t" + bright("red", "  command") + " - a valid " + color("blue", "command\n") +
               "\t" + bright("red", "sub-topic") + " - some " + color("blue", "commands") + " can give you more info about their arguments\n" +
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
      case "info": case "status":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", cmd.toLowerCase()) + "\n\n" +
               "Returns " + bright("blue", "info") + " about the current 'Viewer'\n\n";
      case "list": case "look":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", cmd.toLowerCase()) + " [" + bright("red", "item") + "] [(" + bright("green", "-h") + " / " + bright("green", "-f") + ")] [" + bright("green", "-r ") + bright("red", "start end") + "] [" + bright("green", " -p") + "] [" + bright("green", "-i ") + bright("yellow", "Item") + "]\n\n" +
               "\t   " + bright("red", "item") + " - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", cmd.toLowerCase()) + ")\n" +
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
        return bright("purple", "\nSyntax") + " is: " + bright("blue", "remove ") + bright("red", "item") + " [" + bright("red", "sub-item") + "]\n\n" +
               "\t" + bright("red", "item") + "     - " + bright("cyan", "integer") + " of " + bright("yellow", "Item") + " (see " + bright("blue", "list") + ")\n" +
               "\t" + bright("red", "sub-item") + " - " + bright("cyan", "integer") + " of " + color("yellow", "subItem") + "\n\n" +
               color("blue", "Removes") + " specified " + bright("yellow", "Item") + " from current floor.\n\n";
      case "save": case "export":
        return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", cmd.toLowerCase()) + " [" + bright("cyan", "number") + " ((" + bright("green", "-h") + " / " + bright("green", "--house") + ") / (" + bright("green", "-f") + " / " + bright("green", "--floor") + "))]\n\n" +
               "\t" + bright("cyan", "number") + " - specifies which (floor / house) to " + bright("blue", cmd.toLowerCase()) + "\n\n" +
               color("blue", cmd.substring(0, 1).toUpperCase() + cmd.substring(1).toLowerCase() + "s") + " all data from all houses, or data specified from arguments\n\n";
      case "search": case "find":
        return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", cmd.toLowerCase()) + "\n\n" +
               "Starts a search dialogue for finding items via keywords\n\n";
      case "set":
        return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", "set ") + "[" + bright("red", "variable") + " [" + bright("red", "value") + "] ]\n\n" +
               "\t" + bright("red", "variable") + " - displays what said " + bright("red", "variable") + " is currently set to\n" +
               "\t" + bright("red", "   value") + " - sets " + bright("red", "variable") + " to " + bright("red", "value") + "\n\n" +
               color("blue", "Sets") + " and views variables.\n\n";
      case "up":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", "up ") + "[" + bright("red", "floors") + "]\n\n" +
               "\t" + bright("red", "floors") + " - " + bright("cyan", "integer") + " of how many floors you want to go up\n\n" +
               "Moves to the next floor " + bright("blue", "up") + ", unless you are at the top\n\n";
      case "use":
        return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", "use ") + bright("red", "object\n\n") +
               "\t" + bright("red", "object") + " and object to interact with\n\n" +
               color("blue", "Uses") + " an interactable object.\n\n";
      case "ver": case "version":
        return bright("purple", "\nSyntax") + " is: " + bright("blue", cmd.toLowerCase()) + "\n\n" +
               "Tells you the current " + bright("blue", "version") + " of the Heck Command Interpretter\n\n";
      case "visit":
        return "\n" + bright("purple", "Syntax") + " is: " + bright("blue", "visit ") + bright("red", "house\n\n") +
               "\t" + bright("red", "house") + " - " + bright("cyan", "integer") + " of house to go to\n\n" +
               color("blue", "Visits") + " a specified house.\n\n";
      //start sub-help
      case "top-item":
        ret_val = "\n" + bright("yellow", "Item") + " must be one of these types:\n";
        for (String type : top_types) ret_val += "\n\t* " + type;
        return ret_val + "\n\n";
      case "floor-interact":
        ret_val = "\n" + bright("red", "Object") + " must be one of these types:\n";
        for (String obj : floor_interacts) ret_val += "\n\t* " + obj;
        return ret_val + "\n\n";
      case "bad-sub":
        return "\nInvalid help sub-topic.\n\n";
      default:
        return "\nNo help was found for this command.\n\n";
    }
  }
  public static boolean equalsIgnoreCaseOr(String test, String[] strs) {
    for (int i = 0; i < strs.length; i++) if (test.equalsIgnoreCase(strs[i])) return true;
    return false;
  }
  /*private static boolean matchesAnd(String[] strs, String match) { //Unused
    for (int i = 0; i < strs.length; i++) if (!strs[i].matches(match)) return false;
    return true;
  }*/
  private static boolean canGoInside(String src, String dst) {
    switch (dst.toLowerCase()) {
      case "bookshelf":
        return src.equalsIgnoreCase("book");
      case "container":
        switch (src.toLowerCase()) {
          case "empty":
          case "fridge":
          return false;
          default: return true;
        }
      case "display":
        switch (src.toLowerCase()) {
          case "computer":
          case "console":
          return true;
          default: return false;
        }
      case "empty": return false;
      case "fridge":
        switch (src.toLowerCase()) {
          case "empty":
          case "fridge":
          return false;
          default: return true;
        }
      case "dresser":
        switch (src.toLowerCase()) {
          case "clothing": return true;
          default: return false;
        }
      case "table":
        switch (src.toLowerCase()) {
          case "fridge":
          case "empty":
          case "table":
          return false;
          default: return true;
        }
      default: return false;
    }
  }
  public static Item createContainer(String type) {
    switch (type.toLowerCase()) {
      case "fridge": return new Fridge();
      case "bookshelf": return new Bookshelf();
      case "dresser" : return new Dresser();
      case "table" : return new Table();
      default: return new Container();
    }
  }
  public static Item createClothing(String type) {
    switch (type.toLowerCase()) {
      case "shirt": return new Shirt();
      case "pants": return new Pants();
      default: return new Clothing();
    }
  }
  public static String[][] enviVar = {
    {"interactive", "false", "bool", "user"}, //tells the environment whether or not to do certain things
    {"temperature", "70.0", "double", "system"}, //the ambient house temperature
    {"house", "0", "int", "system"}, //integer representation of current viewer
    {"use_rooms", "false", "bool", "user"}, //off by default so previous users aren't confused as to where their items are
    {"cur_room", "-1", "int", "system"}//, //current room to get items from
  };
  public static int getInput(Scanner scan, int min, int max) throws InvalidRange {
    if (min >= max) throw new InvalidRange(min, max);
    do {
      System.out.print("\n[" + bright("cyan", Integer.toString(min)) + "-" + bright("cyan", Integer.toString(max - 1)) + "] > ");
      String lineIn = scan.nextLine();
      if (lineIn.matches("-?[0-9]+") && Integer.parseInt(lineIn) >= min && Integer.parseInt(lineIn) < max) return Integer.parseInt(lineIn);
    } while (true);
  }
  public static double getInput(Scanner scan, double min, double max) throws InvalidRange {
    if (min >= max) throw new InvalidRange(min, max);
    do {
      System.out.print("\n[" + bright("cyan", Double.toString(min)) + "-" + bright("cyan", Double.toString(max - 1.0)) + "] > ");
      String lineIn = scan.nextLine();
      if (lineIn.matches("-?[0-9]+([.]{1}[0-9]+)?") && Double.parseDouble(lineIn) >= min && Double.parseDouble(lineIn) <= (max - 1.0)) return Double.parseDouble(lineIn);
    } while (true);
  }
  public static String getInput(Scanner scan, String message, ArrayList<String> values, boolean ignoreCase) throws ArrayTooSmall {
    if (values.size() == 0) throw new ArrayTooSmall(1, values.size());
    do {
      System.out.print("\n" + message + "> ");
      String lineIn = scan.nextLine();
      for (String test : values) if ((ignoreCase && lineIn.equalsIgnoreCase(test)) || lineIn.equals(test)) return lineIn;
    } while (true);
  }
  public static String getInput(Scanner scan, String message, String[] values, boolean ignoreCase) throws ArrayTooSmall {
    try {
      return getInput(scan, message, new ArrayList<String>(Arrays.asList(values)), ignoreCase);
    } catch (ArrayTooSmall e) { throw e; }
  }
  public static String getInput(Scanner scan, ArrayList<String> values, boolean ignoreCase) throws ArrayTooSmall {
    try {
      return getInput(scan, "", values, ignoreCase);
    } catch (ArrayTooSmall e) { throw e; }
  }
  public static String getInput(Scanner scan, String[] values, boolean ignoreCase) throws ArrayTooSmall {
    try {
      return getInput(scan, "", values, ignoreCase);
    } catch (ArrayTooSmall e) { throw e; }
  }
  public static int house = 0;

  public static void main (String str[]) throws IOException {
    Scanner scan = new Scanner(System.in);

    String command;
    String[] cmds = {""};

    //This is to keep the contents of my actual house a little more private.
    //Just make your own .java file that returns Items. (See example)
    ItemImport.initializeItems();
    ArrayList<House> houseData = ItemImport.houses;
    ArrayList<Viewer> viewers = new ArrayList<Viewer>();
    for (House h : houseData) viewers.add(new Viewer(h));

    Viewer user = viewers.get(0);
    Boolean here = true;

    while (here) {
      System.out.print("> ");
      command = scan.nextLine();
      String[] temp_arr = command.split(" +");
      cmds = new String[temp_arr.length];
      cmds = temp_arr.clone();
      if (cmds.length > 0) {
        switch (cmds[0].toLowerCase()) {
          case "search":
          case "find":
            String searchItem = "";
            ArrayList<String> keywords = new ArrayList<String>();
            int searchFloor = -1;
            int searchRoom = -2;
            for (int arg = 1; arg < cmds.length - 1; arg += 2) {
              if (equalsIgnoreCaseOr(cmds[arg], new String[] { "-t", "--type" })) {
                searchItem = cmds[arg + 1];
                continue;
              }
              if (searchFloor == -1 && equalsIgnoreCaseOr(cmds[arg], new String[] { "-f", "--floor" })) {
                if (cmds[arg + 1].matches("^\\d+$"))
                  searchFloor = Integer.parseInt(cmds[arg + 1]);
                else
                  System.out.println(bright("red", "floor") + " must be a positive " + bright("cyan", "integer") + ".");
                continue;
              }
              if (searchRoom == -2 && equalsIgnoreCaseOr(cmds[arg], new String[] { "-r", "--room" })) {
                if (cmds[arg + 1].matches("^((-1)|\\d+)$"))
                  searchRoom = Integer.parseInt(cmds[arg + 1]);
                else
                  System.out.println(bright("red", "room") + " must be a positive " + bright("cyan", "integer") + "or" + bright("cyan", "-1") + ".");
                continue;
              }
            }
            System.out.println(searchFloor + " " + searchRoom);
            System.out.println("Please enter 1 -3 keywords: (2 and 3 optional)");
            String[] keys = { "", "", "" };
            do {
              System.out.print("Keyword 1 > ");
              keys[0] = scan.nextLine();
            } while (keys[0].equals(""));
            System.out.print("Keyword 2 > ");
            keys[1] = scan.nextLine();
            if (!keys[1].equals("")) {
              System.out.print("Keyword 3 > ");
              keys[2] = scan.nextLine();
            }
            keywords.add(keys[0]);
            if (!keys[1].equals("")) keywords.add(keys[1]);
            if (!keys[2].equals("")) keywords.add(keys[2]);
            System.out.println("\nSearching for:");
            System.out.print("\"" + keywords.get(0) + "\"");
            for (int key = 1; key < keywords.size(); key++) System.out.print(", \"" + keywords.get(key) + "\"");
            System.out.println();
            System.out.println("In: " + (searchItem.equals("") ? "All" : searchItem) + bright("yellow", " items") + ". On floor: " + (searchFloor == -1 ? "all" : bright("yellow", Integer.toString(searchFloor))) + ". In room: " + (searchRoom == -2 ? "all" : searchRoom == -1 ? "No room" : bright("cyan", Integer.toString(searchRoom))) + ".");
            String output = user.search(searchFloor, searchRoom, searchItem, keywords);
            if (!output.equals(""))
              System.out.print(output);
            else
              System.out.println("No matches found.\n");
            break;
          case "save":
          case "export":
            if (cmds.length > 1) {
              if (cmds.length > 2) {
									switch (cmds[2].toLowerCase()) {
										case "-h":
                    case "--house":
                      if (cmds[1].matches("^\\d+$")) {
                        if (Integer.parseInt(cmds[1]) >= 0 && Integer.parseInt(cmds[1]) < houseData.size()) {
                          File exportFile = new File("exportedHouse.txt");
                          FileWriter fileWriter = new FileWriter(exportFile);
                          fileWriter.write(houseData.get(Integer.parseInt(cmds[1])).export(Integer.parseInt(cmds[1])) + "\n");
                          fileWriter.flush();
                          fileWriter.close();
                          System.out.println("\nHouse " + cmds[1] + " exported.\n");
                        } else System.out.print(bright("red", "house") + " must be greater than or equal to " + bright("cyan", "0") + " and less than " + bright("cyan", Integer.toString(houseData.size())) + ".\n");
                      } else System.out.print(cmds[1] + " is not a valid " + bright("cyan", "integer") + ".\n");
                      break;
										case "-f":
                    case "--floor":
                      if (cmds[1].matches("^\\d+$")) {
                        if (Integer.parseInt(cmds[1]) >= 0 && Integer.parseInt(cmds[1]) < user.curHouse().getFloor(user.curFloor()).size()) {
                          File exportFile = new File("exportedFloor.txt");
                          FileWriter fileWriter = new FileWriter(exportFile);
                          fileWriter.write(user.curHouse().getFloor(Integer.parseInt(cmds[1])).export(Integer.parseInt(cmds[1])) + "\n");
                          fileWriter.flush();
                          fileWriter.close();
                          System.out.println("\nFloors " + cmds[1] + " exported.\n");
                        }
                      }
                      break;
										default:
											System.out.println(cmds[2] + " is not a valid argument.");
											break;
									}
								} else
								System.out.println(cmds[0].toLowerCase() + " accepts 0 or 2 arguments.");
            } else {
              String exportData = "";
              for (int i = 0; i < houseData.size(); i++)
                exportData += houseData.get(i).export(i) + "\n";
              try {
                File exportFile = new File("exportedItems.txt");
                FileWriter fileWriter = new FileWriter(exportFile);
                fileWriter.write(exportData);
                fileWriter.flush();
                fileWriter.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
              System.out.print("\nAll House Data " + cmds[0].toUpperCase().charAt(0) + cmds[0].substring(1).toLowerCase() + (cmds[0].equalsIgnoreCase("export") ? "e" : "") + "d\n\n");
            }
            break;
          case "goto":
            if (cmds.length > 1) {
              if (cmds[1].matches("(-1)|([0-9]+)")) {
                int r = Integer.parseInt(cmds[1]);
                switch (user.goRoom(r)) {
                  case 1: System.out.println(bright("red", "Error: user.goRoom returned 1! Please report this bug!")); break;
                  case 2: System.out.println("There aren't that many rooms on this floor."); break;
                  case 3: System.out.println("\nLeft the room(s).\n");
                  case 0: System.out.println("\nWelcome to room " + cmds[1] + ".\n"); break;
                }
                enviVar[4][1] = cmds[1];
              } else System.out.println(bright("red", "room") + " must be a positive " + bright("cyan", "integer") + ", or " + bright("cyan", "-1") + ".");
            } else {
              System.out.println("\nRooms on this floor:\n");
              ArrayList<String> tmp = user.roomNames();
              for (int i = 0; i < tmp.size(); i++) System.out.println(Integer.toString(i) + ": " + tmp.get(i));
              int new_room = getInput(scan, -1, tmp.size());
              switch(user.goRoom(new_room)) {
                case 3: System.out.println("\nLeft the room(s).\n");
                case 0: System.out.println("\nWelcome to room " + new_room + ".\n"); break;
              }
              enviVar[4][1] = Integer.toString(new_room);
            }
            break;
          case "visit":
            if (cmds.length == 2) {
              if (cmds[1].matches("[0-9]+")) {
                int dst = Integer.parseInt(cmds[1]);
                if (dst < houseData.size()) {
                  user = viewers.get(dst);
                  house = dst;
                  System.out.println("\nWelcome to House " + dst + ".\n");
                } else System.out.println("There aren't that many Houses! (Remember: the first House is #0)");
              } else System.out.println("House number must be a positive " + bright("cyan", "Integer") + ", that is less than " + houseData.size() + ".");
            } else System.out.println("Visit which house? (There are " + houseData.size() + ")");
            break;
          case "use":
            if (cmds.length > 1) {
              switch (cmds[1]) {
                case "light":
                case "lights":
                  if (cmds.length == 2) System.out.println("\n" + user.curHouse().getFloor(user.curFloor()).toggleLights() + "\n");
                  else System.out.println(bright("blue", "use") + " " + cmds[1] + ", does have extra arguments.");
                  break;
                default: System.out.println(cmds[1] + " cannot be '" + color("blue", "used") + "'."); break;
              }
            } else System.out.println(bright("blue", "Use") + " what?");
            break;
          case "set":
            boolean validVar;
            switch (cmds.length) {
              case 1:
                System.out.print("\n\tEnvironment Variables:\n\n");
                for (int i = 0; i < enviVar.length; i++) System.out.println(enviVar[i][0] + " = " + enviVar[i][1]);
                System.out.println();
                break;
              case 2:
                validVar = false;
                for (int i = 0; i < enviVar.length; i++) {
                  if (enviVar[i][0].equalsIgnoreCase(cmds[1])) {
                    System.out.print("\n" + enviVar[i][0] + " = " + enviVar[i][1] + "\n\n");
                    validVar = true;
                  }
                }
                if (!validVar) System.out.print(cmds[1] + " is not a valid variable.\n");
                break;
              case 3:
                validVar = false;
                int i;
                for (i = 0; i < enviVar.length; i++) {
                  if (enviVar[i][0].equalsIgnoreCase(cmds[1])) {
                    validVar = true;
                    break;
                  }
                }
                if (validVar) {
                  if (enviVar[i][3].equals("user")) {
                    switch (enviVar[i][2]) {
                      case "bool":
                        if (equalsIgnoreCaseOr(cmds[2], new String[]{"false", "true", "0", "1"})) {
                          enviVar[i][1] = (equalsIgnoreCaseOr(cmds[2], new String[]{"true", "1"}) ? "true" : "false");
                        } else System.out.print("\n" + enviVar[i][0] + " stores a boolean value, must be true or false. (0 and 1 are acceptible)\n");
                        break;
                      case "int":
                      case "double":
                        if (cmds[2].matches("-?[0-9]+([.]{1}[0-9]+)?")) {
                          double newVal = Double.parseDouble(cmds[2]);
                          enviVar[i][1] = (enviVar[i][2].equals("int") ? Integer.toString((int)newVal) : Double.toString(newVal));
                        } else System.out.print("\n" + enviVar[i][0] + " stores a numeric value, must only contain a number, - is optional, if variable stores a double, you may provide a decimal value\n");
                        break;
                      default:
                        System.out.print("\n" + enviVar[i][0] + " did not have a recognized value type, be cautious, and report this bug.\n");
                      case "string":
                        enviVar[i][1] = cmds[2];
                        break;
                    }
                    //enviVar[i][1] = cmds[2];
                    System.out.print("\n" + enviVar[i][0] + " = " + enviVar[i][1] + "\n\n");
                  } else System.out.print(enviVar[i][0] + " is a system variable, and cannot be changed by the user.\n");
                } else System.out.print(cmds[1] + " is not a valid variable.\n");
                break;
              default: System.out.print("Invalid amount of arguments.\n"); break;
            }
            break;
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
                          case "Container":
                            Item temp_item = ((Container)dst_i).getItem(src);
                            if (!(temp_item instanceof Empty)) {
                              user.addItem(temp_item);
                              System.out.println(((Container)dst_i).removeItem(src));
                            } else System.out.print("The " + bright("yellow", dst_i.subType()) + " doesn't have that many " + color("yellow", "Items") + " in it.\n");
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
                        case "Container":
                          if (canGoInside((src_i instanceof Clothing ? "Clothing" : src_i.subType()), dst_i.subType())) {
                            user.removeItem(src);
                            System.out.println(((Container)dst_i).addItem(src_i));
                          } else System.out.println("A " + src_i.subType() + ", cannot be put-in/attached-to a " + dst_i.subType());
                          break;
                        case "Display":
                          if (src_i instanceof Computer || src_i instanceof Console) System.out.println(((Display)dst_i).connect(src_i));
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
                        Item temp_item = user.cur_item;
                        user.addItem(temp_item);
                        user.goFloor(old_floor);
                        user.removeItem(item);
                        user.cur_item = temp_item;
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
              if (cmds[1].matches("[0-9]+")) {
                Item temp_item = user.cur_item;
                boolean valid_answer = false;
                boolean validItem = false;
                boolean subItem = false;
                if (cmds.length > 2) {
                  subItem = true;
                  if (equalsIgnoreCaseOr(cmds[2], new String[]{"-h", "--house"})) {
                    if (Integer.parseInt(cmds[1]) < houseData.size()) {
                      houseData.remove(Integer.parseInt(cmds[1]));
                      viewers.remove(Integer.parseInt(cmds[1]));
                      System.out.println("\nHouse " + cmds[1] + " removed.\n");
                      if (house > Integer.parseInt(cmds[1])) house--;
                      if (house == Integer.parseInt(cmds[1])) {
                        System.out.print("\nPlease select a pre-existing House to go to now.\n");
                        int newHouse = getInput(scan, 0, houseData.size());
                        user = viewers.get(newHouse);
                      }
                    } else System.out.println("There aren't that many Houses! (Remember: the first House is #0)");
                  } else if (cmds[2].matches("-?[0-9]+")) {
                    switch (user.changeItemFocus(Math.abs(Integer.parseInt(cmds[1])), Math.abs(Integer.parseInt(cmds[2])))) {
                      case 0: validItem = true;
                      case 1: System.out.print("This " + bright("yellow", "Item") + " either has no " + color("yellow", "sub-Items") + " on it, or the " + bright("cyan", "integer") + " is too high\n"); break;
                      case 2: System.out.print("This floor only has " + bright("cyan", Integer.toString(user.floorSize())) + " items on it\n"); break;
                    }
                  } else System.out.print("\"" + cmds[2] + "\" is not a valid " + bright("cyan", "integer\n"));
                } else validItem = user.changeItemFocus(Math.abs(Integer.parseInt(cmds[1])));
                if (validItem) {
                  while (!valid_answer) {
                    System.out.println("\nThis " + bright("yellow", "Item") + " is:\n" + user.cur_item);
                    user.cur_item = temp_item;
                    String yenu = "";
                    try {
                      yenu = getInput(scan, bright("red") + "Are you sure you want to delete this? [Y/N] ", new String[]{"y", "n"}, true);
                    } catch (ArrayTooSmall e) { e.printStackTrace(); }
                    System.out.println(ANSI_RESET);
                    switch (yenu.toUpperCase()) {
                    case "Y":
                      if (subItem)user.removeItem(Math.abs(Integer.parseInt(cmds[1])), Math.abs(Integer.parseInt(cmds[2])));
                      else user.removeItem(Math.abs(Integer.parseInt(cmds[1])));
                    case "N": valid_answer = true;
                    }
                  }
                } else if (!subItem) System.out.print("This floor only has " + bright("cyan", Integer.toString(user.floorSize())) + " items on it\n");
              } else System.out.print("\"" + cmds[1] + "\" is not a valid " + bright("cyan", "integer\n"));
            } else System.out.print(bright("blue", "\nRemove") + " what?\n\n");
            break;
          case "list":
          case "look":
            if (cmds.length > 1) {
              if (equalsIgnoreCaseOr(cmds[1], new String[]{"--hand", "--focus", "-h", "-f"})) System.out.print("\n" + user.viewCurItem() + "\n\n");
              else if (cmds.length > 2 || equalsIgnoreCaseOr(cmds[1], new String[]{"-p", "--page"})) {
                boolean page = false;
                int rangeStart = 0;
                int rangeEnd = user.floorSize();
                String searchType = "*";
                int invalidArg = 0;
                for (int i = 1; i < cmds.length; i++) {
                  if (equalsIgnoreCaseOr(cmds[i], new String[]{"-i", "--item"}) && (i + 1 < cmds.length)) {
                    searchType = cmds[i + 1];
                    i++;
                    continue;
                  }
                  if (equalsIgnoreCaseOr(cmds[i], new String[]{"-r", "--range"}) && (i + 2 < cmds.length)) {
                    if (cmds[i + 1].matches("-?[0-9]+") && cmds[i + 2].matches("-?[0-9]+")) {
                      rangeStart = Math.abs(Integer.parseInt(cmds[i + 1]));
                      rangeEnd = Math.abs(Integer.parseInt(cmds[i + 2]));
                      i += 2;
                      continue;
                    }
                  }
                  if (equalsIgnoreCaseOr(cmds[i], new String[]{"-p", "--page"})) {
                    page = true;
                    continue;
                  }
                  invalidArg = i;
                }
                if (invalidArg == 0) {
                  if (enviVar[0][1].equals("false") || user.curHouse().getFloor(user.curFloor()).getLights()) {
                    if (page) {
                      int pageCount = user.pageCount(rangeStart, rangeEnd, searchType, 20);
                      switch (pageCount) {
                        case 0: System.out.print("No " + bright("yellow", "Items") + " match your criteria.\n"); break;
                        case -1: System.out.print(searchType + " is not a valid type.\n"); break;
                        case -2: System.out.print("Floor is empty.\n"); break;
                        case -3: System.out.print("Range start must be greater than or equal to range end.\n"); break;
                        case -4: System.out.print("Range start must be greater than or equal to 0.\n"); break;
                        default:
                          for (int i = 0; i < pageCount; i++) {
                            System.out.println("\n\tFloor " + color("blue", "Listing") + " - Page " + (i + 1));
                            boolean end_test = (i + 1 < pageCount);
                            System.out.print(user.list(rangeStart, rangeEnd, searchType, 20, i, (enviVar[3][1].equals("true") ? Integer.parseInt(enviVar[4][1]) : -2)));
                            if (end_test) {
                              System.out.print("Press enter to continue > ");
                              scan.nextLine();
                            } else System.out.println();
                          }
                      }
                    } else System.out.print(user.list(rangeStart, rangeEnd, searchType, user.floorSize(), 0, (enviVar[3][1].equals("true") ? Integer.parseInt(enviVar[4][1]) : -2)) + "\n");
                  } else System.out.print("\nYou can't see anything, the floor is completely dark!\n\n");
                } else System.out.print(cmds[invalidArg] + " is not a valid argument.\n");
              } else if (cmds[1].matches("-?[0-9]+")) {
                if (Integer.parseInt(cmds[1]) < user.floorSize()) {
                  Item temp_item = user.cur_item;
                  user.changeItemFocus(Integer.parseInt(cmds[1]));
                  switch (user.cur_item.type()) {
                    case "Container":
                      System.out.println("This " + bright("yellow", "Item") + " is a " + bright("yellow", user.cur_item.subType()) + ", would you like to see:\n" +
                                         "(Y) A specific " + bright("yellow", "Item") + "\n(N) Just the overall contents");
                      while (true) {
                        String yenu = "";
                        try {
                          yenu = getInput(scan, "[Y/N] ", new String[]{"y", "n"}, true);
                        } catch (ArrayTooSmall e) { e.printStackTrace(); }
                        int i_c = ((Container)user.cur_item).size();
                        if (yenu.equalsIgnoreCase("Y") && i_c > 0) {
                          System.out.println("\nWhich " + bright("yellow", "Item") + ":");
                          int im = getInput(scan, 0, i_c);
                          System.out.print("\n" + ((Container)user.cur_item).getItem(im));
                        } else if (yenu.equalsIgnoreCase("N") || ((Container)user.cur_item).size() == 0) System.out.print("\n" + user.viewCurItem());
                        else System.out.print(bright("yellow", user.cur_item.subType()) + " is empty.");
                        System.out.println();
                        if (equalsIgnoreCaseOr(yenu, new String[]{"Y", "N"})) break;
                      }
                      System.out.println();
                      break;
                    case "Display":
                      System.out.print("This " + bright("yellow", "Item") + " is a " + bright("yellow", "Display") + ", would you like to see:\n" +
                                       "(Y) A specific device\n(N) Just the " + bright("yellow", "Display\n\n"));
                      while (true) {
                        System.out.print("[Y/N] > ");
                        String temp = "";
                        try {
                          temp = getInput(scan, "[Y/N] ", new String[]{"y", "n"}, true).toUpperCase();
                        } catch (ArrayTooSmall e) { e.printStackTrace(); }
                        if (temp.equals("Y") && ((Display)user.cur_item).deviceCount() > 0) {
                          System.out.print("\nWhich device:\n\n");
                          while (true) {
                            System.out.print("[" + bright("cyan", "0") + "-" + bright("cyan", Integer.toString((((Display)user.cur_item).deviceCount() - 1))) + "] > ");
                            int dv = Math.abs(scan.nextInt());
                            scan.nextLine();
                            if (dv < ((Display)user.cur_item).deviceCount()) {
                              System.out.print("\n" + ((Display)user.cur_item).getDevice(dv));
                              break;
                            } else System.out.print(bright("yellow", user.cur_item.subType()) + " doesn't have that many " + bright("yellow", "Items"));
                          }
                        } else if (temp.equals("N") || ((Display)user.cur_item).deviceCount() == 0) System.out.print("\n" + user.viewCurItem());
                        System.out.println();
                        if (equalsIgnoreCaseOr(temp, new String[]{"Y", "N"})) break;
                      }
                      System.out.println();
                      break;
                    case "Book":
                    case "Computer":
                    case "Console":
                    case "Bed":
                    case "Clothing":
                    case "Printer":
                      System.out.print("\n" + user.viewCurItem() + "\n\n");
                      break;
                  }
                  user.cur_item = temp_item;
                } else System.out.print("This floor only has " + user.floorSize() + color("yellow", " Items") + " on it\n");
              } else System.out.print("\"" + cmds[1] + "\" is not a valid " + bright("cyan", "integer\n"));
            } else {
              if (enviVar[0][1].equals("false") || user.curHouse().getFloor(user.curFloor()).getLights()) System.out.println(user.list(0, user.floorSize(), "*", user.floorSize(), 0, (enviVar[3][1].equals("true") ? Integer.parseInt(enviVar[4][1]) : -2)));
              else System.out.print("\nYou can't see anything, the floor is completely dark!\n\n");
            }
            break;
          case "add":
            if (cmds.length > 1) {
              switch (cmds[1].toLowerCase()) {
                case "house":
                  if (cmds.length > 2) {
                    if (cmds[2].equalsIgnoreCase("arg")) {
                      int color;
                      System.out.println("\nChoose from these colors:\n");
                      for (int i = 0; i < House.colors.length; i++)
                        System.out.print(bright("cyan", Integer.toString(i)) + ": " + House.colors[i] + " ");
                      System.out.println();
                      color = getInput(scan, 0, House.colors.length);
                      int floors;
                      System.out.print("\nHow many floors will this house have?\n");
                      floors = getInput(scan, 1, 101); //Kinda just had to pick an arbitrary number here
                      House newHouse = new House(color, floors);
                      houseData.add(newHouse);
                      viewers.add(new Viewer(newHouse));
                      System.out.println("\n" + floors + " story, " + House.colors[color] + " House, number " + (houseData.size() - 1) + " added.\n");
                    } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else {
                    House newHouse = new House();
                    houseData.add(newHouse);
                    viewers.add(new Viewer(newHouse));
                    System.out.println("\nHouse " + (houseData.size() - 1) + " added.\n");
                  }
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
                      String year = scan.nextLine();
                      scan.nextLine();
                      temp_book.reset(title, author, (year.matches("[0-9]+") ? Integer.parseInt(year) : 0));
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
                      System.out.println("\nIs the computer turned on?");
                      String is_on = "";
                      try {
                        is_on = getInput(scan, "[Y/N] ", new String[]{"y", "n"}, true).toUpperCase();
                      } catch (ArrayTooSmall e) { e.printStackTrace(); }
                      temp_comp.reset(brand, family, model, (is_on.equals("Y") ? true : false), type, Integer.parseInt(enviVar[4][1]));
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
                      temp_console = new Console(temp_type, com, sys, Integer.parseInt(enviVar[4][1]));
                      System.out.print("\nThis " + bright("yellow", "Console") + " added:\n" + temp_console + "\n\n");
                    } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else System.out.print("\nNew " + bright("yellow", "Console") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                  user.addItem(temp_console);
                  break;
                case "display":
                  Display temp_disp = new Display();
                  if (cmds.length > 2) {
                    if (cmds[2].equalsIgnoreCase("arg")) {
                      System.out.println("\nIs it a Monitor (Y) or a TV (N)?");
                      String is_mon = "";
                      try {
                        is_mon = getInput(scan, "[Y/N] ", new String[]{"y", "n"}, true).toUpperCase();
                      } catch (ArrayTooSmall e) { e.printStackTrace(); }
                      System.out.print("\nType the number for each device connected to this " + bright("yellow", "Display") + " seperated by a space.\n(Optional)\n> ");
                      String[] con_devs = scan.nextLine().split(" +");
                      ArrayList<Integer> added = new ArrayList<Integer>();
                      ArrayList<Integer> not_added = new ArrayList<Integer>();
                      ArrayList<String> not_number = new ArrayList<String>();
                      for (String dev : con_devs) {
                        if (dev.matches("-?[0-9]+")) {
                          int devID = Integer.parseInt(dev);
                          if (devID >= 0 && devID < user.floorSize() && canGoInside(user.getItem(devID).type(), "Display")) added.add(devID);
                          else not_added.add(devID);
                        } else not_number.add(dev);
                      }
                      System.out.print("\nAdded: ");
                      for (int num : added) System.out.print(num + " ");
                      System.out.print("\n\nNot added: ");
                      for (int num : not_added) System.out.print(num + " ");
                      System.out.print("\n\nNot a number: ");
                      for (String str_num : not_number) System.out.print(str_num + " ");
                      System.out.println("\n\nEnter the " + color("yellow", "Display's") + " size in inches (decimals allowed)");
                      double size = getInput(scan, 0.0, 100.0);
                      ArrayList<Item> new_items = new ArrayList<Item>();
                      for (int id : added) new_items.add(user.getItem(id));
                      temp_disp = new Display((is_mon.equals("N") ? false : true), new_items, size, Integer.parseInt(enviVar[4][1]));
                      System.out.print("\nThis " + bright("yellow", "Display") + " added:\n" + temp_disp + "\n\n");
                    } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else System.out.print("\nNew " + bright("yellow", "Display") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                  user.addItem(temp_disp);
                  break;
                case "bed":
                  Bed temp_bed = new Bed();
                  if (cmds.length > 2) {
                    if (cmds[2].equalsIgnoreCase("arg")) {
                      System.out.println("\nIs this " + bright("yellow", "Bed") + " adjustable?");
                      boolean can_move = false;
                      try {
                        can_move = getInput(scan, "[Y/N] ", new String[]{"y", "n"}, true).equalsIgnoreCase("Y");
                      } catch (ArrayTooSmall e) { e.printStackTrace(); }
                      System.out.println();
                      for (int i = 0; i < Bed.types.length; i++)
                        System.out.print("[" + bright("cyan", Integer.toString(i)) + "] " + Bed.types[i] + " ");
                      System.out.println();
                      int bed_type = getInput(scan, 0, Bed.types.length);
                      temp_bed = new Bed(can_move, bed_type, Integer.parseInt(enviVar[4][1]));
                      System.out.print("\nThis " + bright("yellow", "Bed") + " added:\n" + temp_bed + "\n\n");
                    } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else System.out.print("\nNew " + bright("yellow", "Bed") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                  user.addItem(temp_bed);
                  break;
                case "container":
                  System.out.print("\nEnter the " + bright("yellow", "Container") + " sub-type:\n\tie: Container, Bookshelf, Fridge, etc. (Defaults to Container)\n\n> ");
                  String type = scan.nextLine();
                  Item temp_con = createContainer(type);
                  if (cmds.length > 2) {
                    if (cmds[2].equalsIgnoreCase("arg")) {
                      System.out.print("\nType the number for each " + bright("yellow", "Item") + " to be put inside this " + bright("yellow", temp_con.subType()) + " seperated by a space.\n(Optional)\n> ");
                      String[] objs = scan.nextLine().split(" +");
                      System.out.println();
                      ArrayList<Integer> added = new ArrayList<Integer>();
                      ArrayList<Integer> not_added = new ArrayList<Integer>();
                      ArrayList<String> not_number = new ArrayList<String>();
                      for (String itm : objs) {
                        if (itm.matches("[0-9]+")) {
                          int itmID = Integer.parseInt(itm);
                          if (itmID >= 0 && itmID < user.floorSize()) added.add(itmID);
                          else not_added.add(itmID);
                        } else not_number.add(itm);
                      }
                      ArrayList<Item> to_add = new ArrayList<Item>();
                      for (int num : added) to_add.add(user.getItem(num));
                      for (Item i : to_add) {
                        if (canGoInside((i instanceof Clothing ? "Clothing" : i.type()), type)) {
                          user.removeItem(i);
                          ((Container)temp_con).addItem(i);
                        }
                      }
                      System.out.print("\nThis " + bright("yellow", temp_con.subType()) + " created:\n" + temp_con + "\n\n");
                    } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else System.out.print("\nNew " + bright("yellow", temp_con.subType()) + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                  user.addItem(temp_con);
                  break;
                case "clothing":
                  System.out.print("\nEnter the " + bright("yellow", "Clothing") + " sub-type:\n\tie: Clothing, Shirt, Pants, etc. (Defaults to Clothing)\n\n> ");
                  String cloth_type = scan.nextLine();
                  Item temp_cloth = createClothing(cloth_type);
                  if (cmds.length > 2) {
                    if (cmds[2].equalsIgnoreCase("arg")) {
                      System.out.print("\nEnter Clothing color > ");
                      String color = scan.nextLine();
                      ((Clothing)temp_cloth).setColor(color);
                      System.out.print("\nThis " + bright("yellow", temp_cloth.subType()) + " created:\n" + temp_cloth + "\n\n");
                    } else System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else System.out.print("\nNew " + bright("yellow", temp_cloth.subType()) + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                  user.addItem(temp_cloth);
                  break;
                case "printer":
                  Printer tempPrint = new Printer();
                  if (cmds.length > 2) {
                    if (cmds[2].equalsIgnoreCase("arg")) {
                      System.out.print("\nEnter the " + bright("yellow", "Printer") + " details.\n");
                      boolean hasColor = false, canFax = false, canScan = false;
                      System.out.print("\nDoes the " + bright("yellow", "Printer") + " print in color?\n");
                      try {
                        hasColor = getInput(scan, "[Y/N] ", new String[] { "y", "n" }, true).equalsIgnoreCase("Y");
                      } catch (ArrayTooSmall e) { e.printStackTrace(); }
                      System.out.print("\nCan the " + bright("yellow", "Printer") + " send and receive faxes?\n");
                      try {
                        canFax = getInput(scan, "[Y/N] ", new String[] { "y", "n" }, true).equalsIgnoreCase("Y");
                      } catch (ArrayTooSmall e) { e.printStackTrace(); }
                      System.out.print("\nCan the " + bright("yellow", "Printer") + " scan things?\n");
                      try {
                        canScan = getInput(scan, "[Y/N] ", new String[] { "y", "n" }, true).equalsIgnoreCase("Y");
                      } catch (ArrayTooSmall e) { e.printStackTrace(); }
                      tempPrint = new Printer(canFax, canScan, hasColor, Integer.parseInt(enviVar[4][1]));
                      System.out.print("\nThis " + bright("yellow", "Printer") + " added:\n" + tempPrint + "\n\n");
                    } else
                      System.out.print("\nInvalid 2nd argument, did you mean " + bright("green", "arg") + "?\n\n");
                  } else
                    System.out.print("\nNew " + bright("yellow", "Printer") + " added to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n\n");
                  user.addItem(tempPrint);
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
            switch (cmds.length) {
              case 1: System.out.println(user.goUp()); break;
              case 2:
                if (cmds[1].matches("[0-9]+")) {
                  if (user.goFloor(user.curFloor() + Integer.parseInt(cmds[1]))) {
                    System.out.println("\nWelcome to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n");
                    enviVar[4][1] = "-1";
                  }
                  else System.out.println("\nYou are currently on the top floor, floor unchanged.\n");
                } else System.out.println("Argument must be a positive " + bright("cyan", "integer") + ".");
                break;
              default: System.out.println(cmds[0] + " only accepts 1 argument."); break;
            }
            break;
          case "<":
          case "down":
            switch (cmds.length) {
              case 1: System.out.println(user.goDown()); break;
              case 2:
                if (cmds[1].matches("[0-9]+")) {
                  if (user.goFloor(user.curFloor() - Integer.parseInt(cmds[1]))) {
                    System.out.println("\nWelcome to floor " + bright("cyan", Integer.toString(user.curFloor())) + ".\n");
                    enviVar[4][1] = "-1";
                  }
                  else System.out.println("\nYou are currently on the bottom floor, floor unchanged.\n");
                } else System.out.println("Argument must be a positive " + bright("cyan", "integer") + ".");
                break;
              default: System.out.println(cmds[0] + " only accepts 1 argument."); break;
            }
            break;
          case "": break;
          case "help":
            if (cmds.length > 1) {
              if (cmds.length > 2) {
                switch (cmds[1].toLowerCase()) {
                  case "add":
                  case "list":
                    if (cmds[2].equalsIgnoreCase("item")) System.out.print(help("top-item"));
                    else System.out.print(help("bad-sub"));
                    break;
                  case "use":
                    if (cmds[2].equalsIgnoreCase("object")) System.out.print(help("floor-interact"));
                    else System.out.print(help("bad-sub"));
                    break;
                  default: System.out.print(help("."));
                }
              } else System.out.print(help(cmds[1].toLowerCase()));
            } else {
              System.out.println();
              System.out.print("          " + bright("blue", "add") + " - adds an " + bright("yellow") + "Item" + ANSI_RESET + " to the current floor\n");
              System.out.print("       " + bright("blue", "attach") + " - attaches (or detaches) one " + bright("yellow") + "Item" + ANSI_RESET + " to (from) another\n");
              System.out.print("  " + bright("blue", "clear / cls") + " - clears the screen\n");
              System.out.print("         " + bright("blue", "down") + " - goes down 1 floor\n");
              System.out.print("  " + bright("blue", "exit / quit") + " - stops the program\n");
              System.out.print("         " + bright("blue", "goto") + " - changes the current room\n");
              System.out.print("" + bright("blue", "grab / select") + " - sets which " + bright("yellow") + "Item" + ANSI_RESET + " you are currently focused on\n");
              System.out.print("         " + bright("blue", "help") + " - displays this screen, and others\n");
              System.out.print("" + bright("blue", "info / status") + " - shows information about you and the " + bright("yellow") + "House" + ANSI_RESET + " you are currently in\n");
              System.out.print("  " + bright("blue", "list / look") + " - shows the " + bright("yellow") + "Item" + ANSI_RESET + "s on the current floor, or shows info about a\n" +
                               "                specific " + bright("yellow") + "Item\n");
              System.out.print("         " + bright("blue", "move") + " - moves an " + bright("yellow") + "Item" + ANSI_RESET + " to another floor\n");
              System.out.print("       " + bright("blue", "remove") + " - removes an " + bright("yellow") + "Item" + ANSI_RESET + " from the current floor\n");
              System.out.print("" + bright("blue", "save / export") + " - saves item data currently stored in the program\n");
              System.out.print("" + bright("blue", "search / find") + " - searches the current house for items\n");
              System.out.print("          " + bright("blue", "set") + " - used to set variables in the command interpretter\n");
              System.out.print("           " + bright("blue", "up") + " - goes up 1 floor\n");
              System.out.print("          " + bright("blue", "use") + " - interacts with certain items (for interactive mode)\n");
              System.out.print("" + bright("blue", "ver / version") + " - displays information about this command interpretter\n");
              System.out.print("        " + bright("blue", "visit") + " - changes which House (and which viewer) you are in\n");
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
