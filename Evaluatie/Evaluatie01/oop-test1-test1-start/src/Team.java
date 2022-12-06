public class Team {
    private String name;
    private String country;
    private int currentPoints;
    private String continent;
    private String staff;

    public Team(String name, String country) throws Exception {
        if (name==null) throw new Exception("null name");
        if (name.trim().equals("")) throw new Exception("empty name");
        if (country==null) throw new Exception("null country");
        if (country.trim().equals("")) throw new Exception("empty country");
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String toString() {
        return name + ", team for " + country + " started with " + currentPoints + " points";
    }
}
