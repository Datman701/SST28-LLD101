public class InkPen extends Pen {
    public InkPen(String color, Refill refill, OpenStrategy openStrategy, RefillStrategy refillStrategy) {
        super("InkPen", color, refill, openStrategy, refillStrategy);
    }

    @Override
    public void write(String text) {
        System.out.println("[InkPen] Writing with fountain flow: " + text);
    }
}
