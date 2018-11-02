import java.io.*;
import static java.lang.System.*;

import java.util.*;

class Main {
  private static final int verMajor = 1;
  private static final int verMinor = 8;
  private static final int verFix = 0;
  private static String curVer() {
    return verMajor + "." + verMinor + "." + verFix;
  }
  private static final String[] types = {"Book", "Bookshelf", "Computer", "Console", "Display"};
  private static final String os = System.getProperty("os.name");
  private static String help(String cmd) {
    switch (cmd) {
    case "add":
      return "\nSyntax is: add item [constructor arguments...]\n\n" +
             "\titem - must be a valid type\n" +
             "\teach item has optional constructor arguments\n" +
             "\ttype add item arg - to create a non-default item\n\n" +
             "Adds item to the current floor\n\n";
    case "clear":
      return "\nSyntax is: clear\n\n" +
             "Clears the console, and places cursor at home position\n\n";
    case "down":
      return "\nSyntax is: down\n\n" +
             "Moves to the next floor down, unless you are at the bottom\n\n";
    case "exit":
      return "\nSyntax is: exit\n\n" +
             "Stops the program, and returns to your command line/operating environment\n\n";
    case "grab":
      return "\nSyntax is: grab item\n\n" +
             "\titem - integer of item (see list)\n\n" +
             "Changes the \"Viewer\"'s current item\n\n";
    case "info":
      return "\nSyntax is: info\n\n" +
             "Returns info about the current 'Viewer'\n\n";
    case "list":
      return "\nSyntax is: list [item] [(-h / -f)] [-r start end] [-p] [-i item]\n\n" +
             "\titem - integer of item (see list)\n" +
             "\t-h / -f - will show the the \"Viewer\"'s current item\n" +
             "\t\tLong version is --hand or --focus\n" +
             "\t-r - will list items between [start] and [end] (start and end are both\n" +
             "\t     positive integers)\n" +
             "\t\tLong version is --range\n" +
             "\t-p - lists all items on the floor one page at a time (page is defined as\n" +
             "\t     20 lines)\n" +
             "\t\tLong version is --page\n" +
             "\t-i item - lists all items of type 'item' (item string)\n" +
             "\t\tLong version is --item\n\n" +
             "Used for getting info about an item, or multiple items.\n\n";
    case "move":
      return "\nSyntax is: move item floor\n\n" +
             "\titem - integer of item (see list)\n" +
             "\tfloor - integer of floor (or: < for next floor down or > for next floor\n" +
             "\t        down)\n\n" +
             "Moves an item from your current floor to the specified floor.\n\n";
    case "remove":
      return "\nSyntax is: remove item\n\n" +
             "\titem - integer of item (see list)\n\n" +
             "Removes specified item from\n\n";
    case "up":
      return "\nSyntax is: up\n\n" +
             "Moves to the next floor up, unless you are at the top\n\n";
    case "ver":
      return "\nSyntax is: ver\n\n" +
             "Tells you the current version of the Heck Command Interpretter\n\n";
    default:
      return "Code error!!! (Please report, as this message shouldn't be possible to see.)";
    }
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

    while (here) {
      System.out.print("> ");
      command = scan.nextLine();
      String[] temp_arr = command.toLowerCase().split(" +");
      cmds = new String[temp_arr.length];
      cmds = temp_arr.clone();
      switch (cmds[0]) {
      case "move":
        if (cmds.length > 1) {
          if (cmds[1].matches("[0-9]+")) {
            if (cmds.length > 2) {
              if (cmds[2].matches("[0-9]+") || cmds[2].equalsIgnoreCase("<") || cmds[2].equalsIgnoreCase(">")) {
                Item old_item = user.cur_item;
                int item = Integer.parseInt(cmds[1]);
                int destination = (cmds[2].matches("[0-9]+") ? Integer.parseInt(cmds[2]) : (cmds[2].equalsIgnoreCase("<") ? user.curFloor() - 1 : user.curFloor() + 1));
                int old_floor = user.curFloor();
                if (item >= 0 && item <= user.floorSize()) {
                  user.changeItemFocus(item);
                  if (user.goFloor(destination)) {
                    user.addItem(user.cur_item);
                    user.goFloor(old_floor);
                    user.removeItem(item);
                    System.out.print("\nThis item moved to floor " + destination + "\n" + user.cur_item + "\n\n");
                  } else System.out.print("Floor does not exist.\n");
                } else System.out.print("Item does not exist.\n");
                user.cur_item = old_item;
              } else System.out.print("Floor must be an integer, or: < or >.\n");
            } else System.out.print("\nMove it where?\n\n");
          } else System.out.print("Item must be an integer.\n");
        } else System.out.print("\nMove what, and where?\n\n");
        break;
      case "grab":
      case "select":
        if (cmds.length > 1) {
          if (cmds[1].matches("[0-9]+")) {
            if (Integer.parseInt(cmds[1]) < user.floorSize() && Integer.parseInt(cmds[1]) >= 0) {
              user.changeItemFocus(Integer.parseInt(cmds[1]));
              System.out.print("\nThis item selected: (of type " + user.cur_item.type() + ")\n\n");
              System.out.print(user.cur_item + "\n\n");
            } else System.out.print("\"" + cmds[1] + "\" is invalid, must be greater than or equal to 0\nAnd less than the floor item size of: " + user.floorSize() + "\n");
          } else System.out.print("\"" + cmds[1] + "\" is not a valid integer\n");
        } else System.out.print("\nGrab what?\n\n");
        break;
      case "remove":
        if (cmds.length > 1) {
          if (cmds[1].matches("[0-9]+")) {
            if (Integer.parseInt(cmds[1]) < user.floorSize()) {
              if (Integer.parseInt(cmds[1]) <= user.floorSize() && Integer.parseInt(cmds[1]) >= 0) {
                Item temp_item = user.cur_item;
                user.changeItemFocus(Integer.parseInt(cmds[1]));
                if (user.cur_item == temp_item) temp_item = new Empty();
                System.out.print("\nThis Item is:\n" + user.cur_item + "\n\n" +
                                 "Are you sure you want to delete this? [Y/N] > ");
                String yenu = scan.nextLine();
                System.out.println();
                Boolean valid_answer = false;
                while (!valid_answer) {
                  if (yenu.equalsIgnoreCase("Y")) {
                    user.removeItem(Integer.parseInt(cmds[1]));
                    valid_answer = true;
                  }
                  if (yenu.equalsIgnoreCase("N")) valid_answer = true;
                }
                user.cur_item = temp_item;
              } else System.out.print("\nInvalid item number.\n\n");
            } else System.out.print("This floor only has " + user.floorSize() + " items on it\n");
          } else System.out.print("\"" + cmds[1] + "\" is not a valid integer\n");
        } else System.out.print("\nRemove what?\n\n");
        break;
      case "list":
      case "look":
        if (cmds.length > 1) {
          if (cmds[1].equalsIgnoreCase("--hand") ||
              cmds[1].equalsIgnoreCase("--focus") ||
              cmds[1].equalsIgnoreCase("-h") ||
              cmds[1].equalsIgnoreCase("-f")) System.out.print("\n" + user.viewCurItem() + "\n\n");
          else if (cmds[1].equalsIgnoreCase("-i") || cmds[1].equalsIgnoreCase("--item")) {
            if (cmds.length > 2) {
              boolean valid_item = false;
              for (int i = 0; i < types.length; i++) if (cmds[2].equalsIgnoreCase(types[i])) valid_item = true;
              if (valid_item) {
                System.out.print("\n" + user.list(cmds[2]) + "\n\n");
              } else System.out.print(cmds[2] + " is not a valid item type.\n");
            } else System.out.print("No item type specified.\n");
          } else if (cmds[1].equalsIgnoreCase("-p") || cmds[1].equalsIgnoreCase("--page")) {
            for (int i = 0; i < (user.floorSize() / 20 + (user.floorSize() % 20 == 0 ? 0 : 1)); i++) {
              System.out.print("\n\tFloor Listing - Page " + (i + 1) + "\n\n");
              boolean end_test = (20 * (i + 1) < user.floorSize());
              System.out.print(user.list(20 * i, (end_test ? 20 * (i + 1) : user.floorSize())) + "\n\n");
              if (end_test) {
                System.out.print("Press enter to continue > ");
                scan.nextLine();
              }
            }
          } else if (cmds[1].equalsIgnoreCase("-r") || cmds[1].equalsIgnoreCase("--range")) {
            if (cmds.length > 3 && cmds[2].matches("[0-9]+") && cmds[3].matches("[0-9]+")) {
              if (Integer.parseInt(cmds[2]) < Integer.parseInt(cmds[3])) {
                if (Integer.parseInt(cmds[3]) < user.floorSize()) System.out.print("\n" + user.list(Integer.parseInt(cmds[2]), Integer.parseInt(cmds[3]) + 1 ) + "\n\n");
                else System.out.print("There aren't that many objects!\n");
              } else System.out.print("start must be less than end\n");
            } else System.out.print("range requires 2 integers\n");
          } else if (cmds[1].matches("[0-9]+")) {
            if (Integer.parseInt(cmds[1]) < 0) System.out.print("\"" + cmds[1] + "\" is not valid, must be greater than or equal to 0\n");
            else {
              if (Integer.parseInt(cmds[1]) < user.floorSize()) {
                Item temp_item = user.cur_item;
                user.changeItemFocus(Integer.parseInt(cmds[1]));
                switch (user.cur_item.type()) {
                case "Bookshelf":
                  System.out.print("This item is a bookshelf, would you like to see:\n" +
                                   "(Y) A specific book\n(N) Just the bookshelf\n\n");
                  Boolean valid = false;
                  while (!valid) {
                    System.out.print("[Y/N] > ");
                    String temp = scan.nextLine();
                    if (temp.equalsIgnoreCase("Y") && ((Bookshelf)user.cur_item).bookCount() > 0) {
                      System.out.print("\nWhich book:\n\n");
                      Boolean valid2 = false;
                      while (!valid) {
                        System.out.print("[0-" + (((Bookshelf)user.cur_item).bookCount() - 1) + "] > ");
                        int bk = scan.nextInt();
                        scan.nextLine();
                        if (bk > -1 && bk < ((Bookshelf)user.cur_item).bookCount()) {
                          System.out.print("\n" + ((Bookshelf)user.cur_item).getBook(bk));
                          valid = true;
                        }
                      }
                      valid = true;
                    }
                    if (temp.equalsIgnoreCase("N") || ((Bookshelf)user.cur_item).bookCount() == 0) {
                      System.out.print("\n" + user.viewCurItem());
                      valid = true;
                    }
                    System.out.println();
                  }
                  System.out.println();
                  break;
                case "Display":
                  System.out.print("This item is a display, would you like to see:\n" +
                                   "(Y) A specific device\n(N) Just the display\n\n");
                  Boolean valid_num = false;
                  while (!valid_num) {
                    System.out.print("[Y/N] > ");
                    String temp = scan.nextLine();
                    if (temp.equalsIgnoreCase("Y") && ((Display)user.cur_item).deviceCount() > 0) {
                      System.out.print("\nWhich device:\n\n");
                      Boolean valid2 = false;
                      while (!valid2) {
                        System.out.print("[0-" + (((Display)user.cur_item).deviceCount() - 1) + "] > ");
                        int dv = scan.nextInt();
                        scan.nextLine();
                        if (dv > -1 && dv < ((Display)user.cur_item).deviceCount()) {
                          System.out.print("\n" + ((Display)user.cur_item).getDevice(dv));
                          valid2 = true;
                        }
                      }
                      valid_num = true;
                    }
                    if (temp.equalsIgnoreCase("N") || ((Display)user.cur_item).deviceCount() == 0) {
                      System.out.print("\n" + user.viewCurItem());
                      valid = true;
                    }
                    System.out.println();
                  }
                  System.out.println();
                  break;
                case "Book":
                case "Computer":
                case "Console":
                  System.out.print("\n" + user.viewCurItem() + "\n\n");
                  break;
                }
                user.cur_item = temp_item;
              } else System.out.print("This floor only has " + user.floorSize() + " items on it\n");
            }
          } else System.out.print("\"" + cmds[1] + "\" is not a valid integer\n");
        } else System.out.print("\n" + user.list() + "\n\n");
        break;
      case "add":
        if (cmds.length > 1) {
          switch (cmds[1]) {
            case "bookshelf":
              if (cmds.length > 2) {
                if (cmds[2].equals("arg")) {
                  System.out.print("\nHow many books will be on this shelf? > ");
                  int length = scan.nextInt();
                  scan.nextLine();
                  System.out.println();
                  Bookshelf temp_shelf = new Bookshelf();
                  for (int i = 0; i < length; i++) {
                    System.out.print("Book " + i + "\n");
                    System.out.print("\nEnter Book Title > ");
                    String title = scan.nextLine();
                    System.out.print("\nEnter Book Author > ");
                    String author = scan.nextLine();
                    System.out.print("\nEnter Publishing Year > ");
                    int year = scan.nextInt();
                    scan.nextLine();
                    System.out.println();
                    temp_shelf.addBook(new Book(title, author, year));
                  }
                  user.addItem(temp_shelf);
                  System.out.print("\nThis bookshelf created:\n" + temp_shelf + "\n\n");
                } else System.out.print("\nInvalid 2nd argument, did you mean arg?\n\n");
              } else {
                user.addItem(new Bookshelf());
                System.out.print("\nNew bookshelf added to floor " + user.curFloor() + ".\n\n");
              }
              break;
            case "book":
              if (cmds.length > 2) {
                if (cmds[2].equals("arg")) {
                  System.out.print("\nEnter Book Title > ");
                  String title = scan.nextLine();
                  System.out.print("\nEnter Book Author > ");
                  String author = scan.nextLine();
                  System.out.print("\nEnter Publishing Year > ");
                  int year = scan.nextInt();
                  scan.nextLine();
                  Book temp_book = new Book(title, author, year);
                  user.addItem(temp_book);
                  System.out.print("\nThis book added:\n" + temp_book + "\n\n");
                } else System.out.print("\nInvalid 2nd argument, did you mean arg?\n\n");
              } else {
                user.addItem(new Book());
                System.out.print("\nNew book added to floor " + user.curFloor() + ".\n\n");
              }
              break;
            case "computer":
              if (cmds.length > 2) {
                if (cmds[2].equals("arg")) {
                  System.out.print("\nWhat kind of computer is it? (Desktop, Laptop, etc) > ");
                  String type = scan.nextLine();
                  System.out.print("\nComputer Brand (ie: HP, Microsoft) > ");
                  String brand = scan.nextLine();
                  System.out.print("Computer Family (ie: Pavilion, Surface) > ");
                  String family = scan.nextLine();
                  System.out.print("Computer Model (ie: dv6, Pro 3) > ");
                  String model = scan.nextLine();
                  System.out.print("\nIs it on? (Invalid input will default to no)\n Yes or no? [Y/N] > ");
                  String is_on = scan.nextLine();
                  Computer temp_comp = new Computer(brand, family, model, (is_on.equalsIgnoreCase("Y") ? true : false), type);
                  user.addItem(temp_comp);
                  System.out.print("\nThis computer added:\n" + temp_comp + "\n\n");
                } else System.out.print("\nInvalid 2nd argument, did you mean arg?\n\n");
              } else {
                user.addItem(new Computer());
                System.out.print("\nNew computer added to floor " + user.curFloor() + ".\n\n");
              }
              break;
            case "console":
              if (cmds.length > 2) {
                if (cmds[2].equals("arg")) {
                  System.out.print("0: " + Console.types[0]);
                  for (int i = 1; i < Console.types.length; i++) System.out.print(" " + i + ": " + Console.types[i]);
                  System.out.println();
                  System.out.print("\nEnter Console Type > ");
                  int temp_type = scan.nextInt();
                  scan.nextLine();
                  System.out.print("\nEnter Console Manufacturer (ie Nintendo) > ");
                  String com = scan.nextLine();
                  System.out.print("\nEnter Console Name (ie GameCube) > ");
                  String sys = scan.nextLine();
                  Console temp_console = new Console(temp_type, com, sys);
                  user.addItem(temp_console);
                  System.out.print("\nThis Console added:\n" + temp_console + "\n\n");
                } else System.out.print("\nInvalid 2nd argument, did you mean arg?\n\n");
              } else {
                user.addItem(new Console());
                System.out.print("\nNew console added to floor " + user.curFloor() + ".\n\n");
              }
              break;
            case "display":
              if (cmds.length > 2) {
                if (cmds[2].equals("arg")) {
                  System.out.print("\nIs it a Monitor (Y) or a TV (N)?\nWill default to (Y)es if next input is invalid.\n[Y/N] > ");
                  String is_mon = scan.nextLine();
                  System.out.print("\nType the number for each device connected to this Display seperated by a space.\n(Optional)\n > ");
                  String[] con_devs = scan.nextLine().split(" +");
                  ArrayList<Item> valid_devs = new ArrayList<Item>();
                  ArrayList<Integer> added = new ArrayList<Integer>();
                  ArrayList<Integer> not_added = new ArrayList<Integer>();
                  ArrayList<String> not_number = new ArrayList<String>();
                  for (String dev : con_devs) {
                    if (dev.matches("[0-9]+")) {
                      int devID = Integer.parseInt(dev);
                      if (devID >= 0 && devID < user.floorSize()) added.add(devID);
                      else not_added.add(devID);
                    } else not_number.add(dev);
                  }
                  System.out.print("\nAdded: ");
                  for (int num : added) System.out.print(num + " ");
                  System.out.println();
                  System.out.print("\nNot added: ");
                  for (int num : not_added) System.out.print(num + " ");
                  System.out.println();
                  System.out.print("\nNot a number: ");
                  for (String str_num : not_number) System.out.print(str_num + " ");
                  System.out.print("\n\n");
                  System.out.print("Enter the displays size in inches (decimals allowed) > ");
                  double size = scan.nextDouble();
                  scan.nextLine();
                  ArrayList<Item> new_items = new ArrayList<Item>();
                  for (int id : added) new_items.add(user.getItem(id));
                  Display temp_disp = new Display((is_mon.equalsIgnoreCase("N") ? false : true), new_items, size);
                  user.addItem(temp_disp);
                  System.out.print("\nThis display added:\n" + temp_disp + "\n\n");
                } else System.out.print("\nInvalid 2nd argument, did you mean arg?\n\n");
              } else {
                user.addItem(new Display());
                System.out.print("\nNew display added to floor " + user.curFloor() + ".\n\n");
              }
              break;
            default:
              System.out.print("\"" + cmds[1] + "\" is not a valid Item type:\n");
              for (int i = 0; i < cmds.length; i++) System.out.print(cmds[i] + " ");
              System.out.print("\n");
              System.out.print(help("add"));
              break;
          }
        } else System.out.print("\nInvalid syntax, requires at least one argument\n\n");
        break;
      case "status":
      case "info":
        System.out.println("\n" + user + "\n");
        break;
      case "up":
        if (user.goUp()) System.out.println("\nWelcome to floor " + user.curFloor() + ".\n");
        else System.out.println("\nYou are currently on the top floor, floor unchanged.\n");
        break;
      case "down":
        if (user.goDown()) System.out.println("\nWelcome to floor " + user.curFloor() + ".\n");
        else System.out.println("\nYou are currently on the bottom floor, floor unchanged.\n");
        break;
      case "": break;
      case "help":
        if (cmds.length > 1) {
          switch (cmds[1]) {
          case "add": System.out.print(help("add")); break;
          case "clear": case "cls": System.out.print(help("clear")); break;
          case "down": System.out.print(help("down")); break;
          case "exit": case "quit": System.out.print(help("exit")); break;
          case "grab": case "select": System.out.print(help("grab")); break;
          case "info": case "status": System.out.print(help("info")); break;
          case "list": case "look": System.out.print(help("list")); break;
          case "move": System.out.print(help("move")); break;
          case "remove": System.out.print(help("remove")); break;
          case "up": System.out.print(help("up")); break;
          case "ver": case "version": System.out.print(help("ver")); break;
          default: System.out.print("\nNo help was found for this command.\n\n"); break;
          }
        } else {
          System.out.print("\nadd - adds item to the current floor\n");
          System.out.print("clear / cls - clears the screen\n");
          System.out.print("down - goes down 1 floor\n");
          System.out.print("exit / quit - stops the program\n");
          System.out.print("grab / select - sets which item you are currently focused on\n");
          System.out.print("help - displays this screen\n");
          System.out.print("info / status - shows information about you and the house you are currently in\n");
          System.out.print("list / look - shows the items on the current floor, or shows info about a\n" +
                           "              specific item\n");
          System.out.print("move - moves an item to another floor\n");
          System.out.print("remove - removes an object from the current floor\n");
          System.out.print("up - goes up 1 floor\n");
          System.out.print("ver / version - displays information about this command interpretter\n");
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
        System.out.print("\nHeck Command Interpretter\n\tVersion " + curVer() + "\n\n");
        break;
      default:
        System.out.print("\"" + cmds[0] + "\" is not a valid command:\n");
        for (int i = 0; i < cmds.length; i++) System.out.print(cmds[i] + " ");
        System.out.print("\n");
        break;
      }
    }
    /*Item temp_item = my_house.getItem(1, 0);
    if (temp_item instanceof Bookshelf) System.out.print(temp_item.getBook(0));*/
		scan.close();
  }
}
