public class AdventureCommandWords extends CommandWords {
    protected void initializeCommands() {
        validCommands.put("go", CommandWord.GO);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("exit", CommandWord.QUIT);
        validCommands.put("?", CommandWord.HELP);
        validCommands.put("help", CommandWord.HELP);
    }
}
