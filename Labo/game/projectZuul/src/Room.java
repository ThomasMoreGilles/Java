import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Room {
    private final List<Item> items = new ArrayList<>();
    private final String description;
    private final Map<String, Room> exits = new HashMap<>();

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open courtyard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
    }

    public void addItem(Item item) {
        if (item != null) items.add(item);
    }

    public boolean hasItem(String name) {
        return items.stream().anyMatch(i -> i.getName().equals(name));
    }

    public Item getItem(String name) {
        Item item = items.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (item != null && item.isMoveable()) {
            items.removeIf(i -> i.getName().equals(name));
        }
        return item;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void setExit(String direction, Room room) {
        if (room != null) {
            exits.put(direction, room);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        StringBuilder fullDescription = new StringBuilder(description);
        if (!items.isEmpty()) {
            fullDescription.append(" which contains items:");
            for (Item i : items) {
                fullDescription.append(System.lineSeparator())
                        .append("  ")
                        .append(i.getItemDescription());
            }
        }
        fullDescription.append(System.lineSeparator()).append(getExitString());
        return fullDescription.toString();
    }

    public String getExitString() {
        return "Exits: " + Color.RED + String.join(" ", exits.keySet()) +Color.RESET;
    }
}
