public class TestTeam {
    public static void main(String[] args) throws Exception {
        Team t = new Team("Red Devils", "Belgium");
        t.setCurrentPoints(1862);
        System.out.println(t.toString());
    }
}
