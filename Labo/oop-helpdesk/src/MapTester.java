import java.util.HashMap;

public class MapTester {
    private HashMap<String, String> phonebook;

    public MapTester() {
        phonebook = new HashMap<>();
        phonebook.put("Qikai", "0478 45 34 21");
        phonebook.put("Justine", "0486 67 54 09");
        phonebook.put("Jonatan", "0456 67 43 21");
    }

    public void enterNumber(String name, String number) {
        phonebook.put(name, number);
    }

    public int size() {
        return phonebook.size();
    }

    public String lookupNumber(String name) {
        return phonebook.get(name);
    }

    public boolean isContact(String name) {
        return false;
    }

    public static void main(String[] args) {
        MapTester mt = new MapTester();
        mt.enterNumber("Lander", "0479 00 67 43");
        mt.enterNumber("Ariola", "0485 65 98 67");
        mt.enterNumber("Ernazar", "0473 56 30 72");
        mt.enterNumber("Jonatan", "geen telefoon");
        mt.enterNumber("Alperen", "0473 56 30 72");
        System.out.println("Phonebook heeft " + mt.size() + " entries");
        System.out.println(mt.lookupNumber("Justine"));
        System.out.println(mt.lookupNumber("Ariola"));
        System.out.println(mt.lookupNumber("Jonatan"));
        System.out.println("Ernazar heeft telefoonnummer : " + mt.lookupNumber("Ernazar"));
        System.out.println("Alperen heeft telefoonnummer : " + mt.lookupNumber("Alperen"));
        System.out.println("Marc ? " + mt.isContact("Marc"));
        System.out.println("Qikai ? " + mt.isContact("Qikai"));
    }
}
