public enum CommandWord {
    GO("go"), TAKE("take"), DROP("drop"), LOOK("look"), EAT("eat"), QUIT("quit", "exit"), HELP("?", "help"), UNKNOWN("");

    private final String[] commands;

    CommandWord(String... commands) {
        this.commands = commands;
    }

    public String[] getCommands() {
        return commands;
    }
}

