import java.util.HashSet;

public class Organizer {
    private HashSet<Team> teams;

    public Organizer() {
        this("teams.csv");
    }

    public Organizer(String file) {
        teams = new HashSet<>();
        TeamReader tr = new TeamReader(file);
        for(TeamEntry te : tr.getEntries()) {
            String[] data = te.getData();
            Team t = new Team(data[TeamEntry.NICKNAME], data[TeamEntry.COUNTRY]);
            t.setCurrentPoints(Integer.parseInt(data[TeamEntry.POINTS]));
            t.setContinent(data[TeamEntry.CONTINENT]);
            t.setSign(data[TeamEntry.SIGN]);
            t.setLeague(data[TeamEntry.LEAGUE]);
            teams.add(t);
            //fillLeagues();
        }
    }
}
