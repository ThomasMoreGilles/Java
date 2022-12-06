
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

    public String getInitials() {
        Staff obj = new Staff();
        obj.setName("Roberto Martinez");
        String fullName = obj.getName();

        String initials = "";

        String[] a = fullName.split(" ");
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i].substring(0, 1);
            if (a[i] == a[i].toUpperCase()) {
                initials += a[i] + ".";
            }
        }
        return initials;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}