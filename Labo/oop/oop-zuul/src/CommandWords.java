import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private HashMap<String, CommandWord> validCommands = new HashMap<>();

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands.put("go", CommandWord.GO);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("exit", CommandWord.QUIT);
        validCommands.put("?", CommandWord.HELP);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public CommandWord getCommandWord(String aString) {
        if (!isCommand(aString)) return CommandWord.UNKNOWN;
        return validCommands.get(aString);
    }

    public String getCommandString() {
        String returnString = "";
        for(String command : validCommands.keySet()) returnString += command + " ";
        return returnString;
    }
}
