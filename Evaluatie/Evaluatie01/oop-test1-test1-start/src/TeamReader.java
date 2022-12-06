import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamReader {
    private String format;
    private final ArrayList<TeamEntry> entries;

    public TeamReader() {
        this("teams.csv");
    }

    public TeamReader(String filename) {
        entries = new ArrayList<>();

        try {
            File pFile = new File("");
            File teamFile = new File(pFile.getAbsolutePath() + "/data/" + filename);
            Scanner teamScanner = new Scanner(teamFile);
            while (teamScanner.hasNextLine()) {
                String teamLine = teamScanner.nextLine();
                TeamEntry entry = new TeamEntry(teamLine);
                entries.add(entry);
            }
            teamScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Er dook een probleem op: " + e.getMessage());
        }
    }

    public ArrayList<TeamEntry> getEntries() {
        return entries;
    }

    public static void main(String[] args) {
        try {
            TeamReader tr = new TeamReader();
            for (TeamEntry te : tr.getEntries()) {
                String[] teamData = te.getData();
                System.out.println(teamData[TeamEntry.NICKNAME] + ", the team for " + teamData[TeamEntry.COUNTRY] + " has " + teamData[TeamEntry.POINTS] + " points");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
