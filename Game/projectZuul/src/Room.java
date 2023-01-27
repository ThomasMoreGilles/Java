import java.util.ArrayList;
import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open courtyard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        exits = new HashMap<>();
        items = new ArrayList<>();
        this.description = description;
    }

    public void addItem(Item item) {
        if (item!=null) items.add(item);
    }

    public boolean hasItem(String name) {
        for(Item i : items) {
            if (i.getName().equals(name)) return true;
        }
        return false;
    }

    public Item getItem(String name) {
        int itemIndex = -1;
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                itemIndex = i;
                break;
            }
        }
        if (itemIndex > -1) {
            Item checkItem = items.get(itemIndex);
            if (checkItem.isMoveable()) items.remove(itemIndex);
            return checkItem;
        }
        return null;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void setExit(String direction, Room room) {
        if (room!=null) exits.put(direction, room);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public String getLongDescription() {
        String fullDescription = getDescription();
        if (!items.isEmpty()) {
            fullDescription += " which contains items:";
            for(Item i : items)   {
                fullDescription += System.lineSeparator() + "  " + i.getItemDescription();
            }
        }
        fullDescription += System.lineSeparator() + getExitString();
        return fullDescription;
    }

    public String getExitString() {
        String returnString = "Exits: ";
        for (String exitString : exits.keySet()) {
            returnString += exitString + " ";
        }
        return returnString;
    }

}
