import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public abstract class CommandWords {
    protected HashMap<String, CommandWord> validCommands = new HashMap<>();
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        initializeCommands();
    }

    /**
     * Initialize the list of valid commands for the game.
     * This method should be implemented by subclasses to add specific commands.
     */
    protected abstract void initializeCommands();

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    public CommandWord getCommandWord(String aString) {
        if (!isCommand(aString)) return CommandWord.UNKNOWN;
        return validCommands.get(aString);
    }

    public String getCommandString() {
        String returnString = "";
        for (String command : validCommands.keySet()) returnString += command + " ";
        return returnString;
    }
}