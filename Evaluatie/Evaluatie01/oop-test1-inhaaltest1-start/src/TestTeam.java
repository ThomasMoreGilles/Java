public class TestTeam {
    public static void main(String[] args) throws Exception {
        Team t1 = new Team("Red Devils", "Belgium");
        Team t2 = new Team("Selecção das Quinas", "Portugal");
        t1.setSign("B");
        t1.setLeague("UEFA");
        t2.setSign("P");
        t2.setLeague("UEFA");
        System.out.println(t1.toString());
        System.out.println(t2.toString());
    }
}
