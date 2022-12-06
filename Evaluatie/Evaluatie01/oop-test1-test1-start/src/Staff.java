
public class Staff {
    private String name;
    private String role;
    private Integer age;

    // Getters
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Integer getAge() {
        return age;
    }

    // Setter
    public void setName(String N) {
        this.name = N;
    }

    public void setRole(String R) {
        this.role = R;
    }

    public void setAge(Integer A) {
        this.age = A;
    }

    public String toString() {
        Staff obj = new Staff();

        obj.setName("Roberto Martinez");
        obj.setRole("trainer");
        obj.setAge(49);

        String output = obj.getRole() + " : " + obj.getName() + "(" + obj.getAge() + ")";
        return output;
    }
}