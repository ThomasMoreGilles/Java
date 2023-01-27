import java.util.Objects;

public class Item {
    private final String name;
    private final String description;
    private final double weight;
    private boolean moveable;

    public Item(String name, String description, double weight) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.weight = weight;
        this.moveable = true;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public String getItemDescription() {
        return String.format("%s (%s) with weight of %.2f kg", name, description, weight);
    }
}
