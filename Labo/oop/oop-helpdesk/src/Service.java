import java.util.HashMap;

public class Service {
    public final static String COMPANY = "company";
    public final static String NAME = "name";
    public final static String WELCOME = "welcome";
    public final static String GOODBYE = "goodbye";
    public final static String COMMAND = "command";
    private HashMap<String, String> values;
    
    /**
     * Verwerk de service
     * @param map Alle key-value paren die werden gevonden voor de service
     */
    public Service(HashMap<String, String> map) throws Exception {
        if (!map.containsKey(COMPANY)) throw new Exception("This is not a valid service");
        values = new HashMap<>();
        if (!map.containsKey(NAME)) {
            map.put(NAME, "|company| support system");
        }
        if (!map.containsKey(WELCOME)) {
            map.put(WELCOME, "Welcome to |name|. Please type '|command|' to exit our service.");
        }
        if (!map.containsKey(GOODBYE)) {
            map.put(GOODBYE, "Nice talking toe you. Bye!");
        }
        if (!map.containsKey(COMMAND)) {
            map.put(COMMAND, "quit");
        }
        for (String key : map.keySet()) {
            String value = map.get(key);
            value = value.replace("\\n", System.lineSeparator());
            value = value.replace("|"+COMPANY+"|", map.get(COMPANY));
            value = value.replace("|"+NAME+"|", map.get(NAME).replace("|"+COMPANY+"|",  map.get(COMPANY)));
            value = value.replace("|"+COMMAND+"|", map.get(COMMAND));
            values.put(key, value);
        }
    }
    
    /**
     * Deze methode retourneert één value van de service
     */
    public String getValue(String key) {
        if (values.containsKey(key)) return values.get(key);
        return null;
    }
    
    public String toString() {
        String ret = "SERVICE";
        for (String key : values.keySet()) {
            ret += "\n  " + key + " = " + values.get(key);
        }
        return ret;
    }
}
