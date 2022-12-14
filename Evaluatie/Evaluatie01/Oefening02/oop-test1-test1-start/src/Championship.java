import java.util.HashSet;

public class Championship {
    private HashSet<Team> teams;

    public Championship() throws Exception {
        this("teams.csv");
    }

    public Championship(String file) throws Exception {
        teams = new HashSet<>();
        TeamReader tr = new TeamReader(file);
        for(TeamEntry te : tr.getEntries()) {
            String[] data = te.getData();
            Team t = new Team(data[TeamEntry.NICKNAME], data[TeamEntry.COUNTRY]);
            t.setCurrentPoints(Integer.parseInt(data[TeamEntry.POINTS]));
            t.setContinent(data[TeamEntry.CONTINENT]);
            teams.add(t);
        }
    }
}
