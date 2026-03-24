public class Refill {
    private final String color;
    private final String inkType;

    public Refill(String color, String inkType) {
        this.color = color;
        this.inkType = inkType;
    }

    public String getColor() {
        return color;
    }

    public String getInkType() {
        return inkType;
    }
}
