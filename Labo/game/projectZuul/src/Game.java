import java.util.Scanner;

public class Game {
    private final Parser parser;
    private final Player player;
    public Game() {
        System.out.print("What is your name? ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        player = new Player(Color.WHITE_BRIGHT +playerName+Color.RESET);
        createRooms();
        parser = new Parser();
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
    private void createRooms() {
        Room outside, theater, pub, lab, office, cellar;
        Item keg = new Item("beer", "keg of beer", 45.5);

        outside = new Room("outside the main entrance of the hospital");
        Item promoboard = new Item("promoboard", "Hospital promoboard", 2.3);
        outside.addItem(promoboard);
        promoboard.setMoveable(false);
        outside.addItem(new Item("ashtray", "Big yellow ashtray", 4.6));
        theater = new Room("in a lecture theater");
        pub = new Room("in the hospital pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("the stock cellar under the pub");
        cellar.addItem(keg);
        cellar.addItem(new Item("water", "crate of water", 7.3));
        cellar.addItem(new Item("cola", "crate of cola", 7.3));
        cellar.addItem(new Item("cola", "crate of cola", 7.3));
        cellar.addItem(new Item("cola", "crate of cola", 7.3));

        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        pub.setExit("down", cellar);
        cellar.setExit("up", pub);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        player.setCurrentRoom(outside);  // start game outside
    }
    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case UNKNOWN -> {
                System.out.println("I don't know what you mean...");
                return false;
            }
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case TAKE -> take(command);
            case DROP -> drop(command);
            case LOOK -> look();
            case EAT -> eat();
            case QUIT -> wantToQuit = quit(command);
            default -> {
            }
        }
        return wantToQuit;
    }

    private void look() {
        printLocationInfo();
    }

    private void eat() {
        System.out.println("You have eaten and are not hungry anymore");
    }

    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        int result = player.take(itemName);
        if (result == Player.ITEM_GONE) {
            printLocationInfo();
        } else if (result == Player.ITEM_NOTPRESENT) {
            System.out.println("There is no item with the name " + itemName);
        } else {
            System.out.println("The item with name " + itemName + " is not moveable");
        }
    }

    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        int result = player.drop(itemName);
        if (result == Player.ITEM_GONE) {
            printLocationInfo();
        } else if (result == Player.ITEM_NOTPRESENT) {
            System.out.println("You don't have an item with the name " + itemName);
        }
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   " + parser.getCommandString());
    }
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }

    private void printLocationInfo() {
        System.out.println("Player " + player.getName() + " is " + player.getCurrentRoom().getLongDescription());
        System.out.println(player.getLongDescription());
        System.out.println();
    }
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}
