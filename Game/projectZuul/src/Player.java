import java.util.ArrayList;

public class Player {
    public static final int ITEM_GONE = 0;
    public static final int ITEM_NOTPRESENT = 1;
    public static final int ITEM_NOTMOVEABLE = 2;
    private String name;
    private Room currentRoom;
    private ArrayList<Item> bag = new ArrayList<>();

    public Player(String name) {
        this.name = name;
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

    public int take(String name) {
        if (currentRoom.hasItem(name)) {
            Item item = currentRoom.getItem(name);
            if (item!=null) {
                if (!item.isMoveable()) return ITEM_NOTMOVEABLE; // is niet moveable
                bag.add(item);
            }
            return ITEM_GONE; // is gelukt
        }
        return ITEM_NOTPRESENT; // bestaat niet
    }

    public String getLongDescription() {
        String returnString = "Player " + name + " has following items in his bag:";
        for (Item i : bag) {
            returnString += System.lineSeparator() + "  " + i.getItemDescription();
        }
        return returnString;
    }
}
