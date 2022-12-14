public class TestServices {
    public static void main(String[] args) {
        try {
            ServiceReader r = new ServiceReader();
            for (Service s : r.getServices()) {
                System.out.println(s.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}