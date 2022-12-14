public class Publicity {
    private String brand;
    private String message;

    public Publicity(String brand, String message) {
        this.brand = brand;
        this.message = message;
    }

    public String getBrand() {
        return brand;
    }

    /**
     * Get display with all details of this publicity.
     */
    public String getDisplay() {
        return brand + " - " + message;
    }
}
