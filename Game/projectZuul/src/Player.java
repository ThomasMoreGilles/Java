import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int ITEM_GONE = 0;
    public static final int ITEM_NOTPRESENT = 1;
    public static final int ITEM_NOTMOVEABLE = 2;
    private final String name;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    public Player(String name) {
        this.name = name;
        inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public int take(String name) {
        Item item = currentRoom.getItem(name);
        if (item != null) {
            if (!item.isMoveable()) return ITEM_NOTMOVEABLE;
            inventory.add(item);
            return ITEM_GONE;       // is gelukt
        }
        return ITEM_NOTPRESENT;     //bestaat niet
    }

    public int drop(String itemName) {
        Item item = getItem(itemName);
        if(item == null) {
            return ITEM_NOTPRESENT;
        }
        inventory.remove(item);
        currentRoom.addItem(item);
        return ITEM_GONE;
    }

    private Item getItem(String itemName) {
        for (Item item : inventory) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    public String getLongDescription() {
        StringBuilder sb = new StringBuilder("Player " + name + " has following items in his bag:");
        if (inventory.isEmpty()) {
            sb.append("\n   None");
        } else {
            for (Item i : inventory) {
                sb.append("\n   ").append(i.getItemDescription());
            }
        }
        return sb.toString();
    }
}