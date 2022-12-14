import java.util.Scanner;

public class TeamEntry {
    private final String[] dataValues;

    public static final int NICKNAME = 0;
    public static final int COUNTRY = 1;
    public static final int POINTS = 2;
    public static final int CONTINENT = 3;
    private static final int NUMBER_OF_FIELDS = 4;

    public TeamEntry(String lineTeam)
    {
        dataValues = new String[NUMBER_OF_FIELDS];
        tokenize(lineTeam, dataValues);
    }

    public String[] getData()
    {
        return dataValues;
    }

    public static void tokenize(String teamLine, String[] dataLine) {
        try {
            Scanner tokenizer = new Scanner(teamLine).useDelimiter(";");
            for (int i = 0; i < dataLine.length; i++) {
                String s = tokenizer.next();
                if (s.length()>0 && s.charAt(0) == '"') {
                    s = s.substring(1);
                }
                if (s.length()>0 && s.charAt(s.length() - 1) == '"') {
                    s = s.substring(0, s.length() - 1);
                }
                dataLine[i] = s;
            }
        } catch (java.util.NoSuchElementException e) {
            System.out.println("dataLine is incomplete: " + dataLine);
            throw e;
        }
    }
}
