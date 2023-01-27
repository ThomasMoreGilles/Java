import java.util.Scanner;

public class Game
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        System.out.print("What is your name? ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, cellar;
        Item keg = new Item("beer", "keg of beer", 45.5);

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        Item promoboard = new Item("promoboard", "University promoboard", 2.3);
        outside.addItem(promoboard);
        promoboard.setMoveable(false);
        outside.addItem(new Item("ashtray", "Big yellow ashtray", 4.6));
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("the stock cellar under the pub");
        cellar.addItem(keg);
        cellar.addItem(new Item("water", "crate of water", 7.3));
        cellar.addItem(new Item("cola", "crate of cola", 7.3));
        cellar.addItem(new Item("cola", "crate of cola", 7.3));
        cellar.addItem(new Item("cola", "crate of cola", 7.3));

        // initialise room exits
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

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                return false;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                break;
            case LOOK:
                look();
                break;
            case EAT:
                eat();
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            default:
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
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        int result = player.take(itemName);
        if (result==Player.ITEM_GONE) {
            printLocationInfo();
        } else if(result==Player.ITEM_NOTPRESENT) {
            System.out.println("There is no item with the name " + itemName);
        } else {
            System.out.println("The item with name " + itemName + " is not moveable");
        }
    }


    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   " + parser.getCommandString());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }

    private void printLocationInfo() {
        System.out.println("Player " + player.getName() + " is " + player.getCurrentRoom().getLongDescription());
        System.out.println(player.getLongDescription());
        System.out.println();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
