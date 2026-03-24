public class GelPen extends Pen {
    public GelPen(String color, Refill refill, OpenStrategy openStrategy, RefillStrategy refillStrategy) {
        super("GelPen", color, refill, openStrategy, refillStrategy);
    }

    @Override
    public void write(String text) {
        System.out.println("[GelPen] Writing smoothly: " + text);
    }
}
