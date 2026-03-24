public abstract class Pen {
    private final String type;
    private final String color;
    private final Refill refill;
    private final OpenStrategy openStrategy;
    private final RefillStrategy refillStrategy;

    protected Pen(String type, String color, Refill refill, OpenStrategy openStrategy, RefillStrategy refillStrategy) {
        this.type = type;
        this.color = color;
        this.refill = refill;
        this.openStrategy = openStrategy;
        this.refillStrategy = refillStrategy;
    }

    public abstract void write(String text);

    public void open() {
        openStrategy.open();
    }

    public void close() {
        openStrategy.close();
    }

    public void refill() {
        refillStrategy.refill(refill);
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public Refill getRefill() {
        return refill;
    }
}
