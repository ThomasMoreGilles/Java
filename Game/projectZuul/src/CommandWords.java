import java.util.HashMap;

public abstract class CommandWords {
    protected HashMap<String, CommandWord> validCommands = new HashMap<>();

    /**
     * Initialize the list of valid commands for the game.
     * This method should be implemented by subclasses to add specific commands.
     */
    protected abstract void initializeCommands();

    public CommandWords() {
        initializeCommands();
    }

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