public class BallPen extends Pen {
    public BallPen(String color, Refill refill, OpenStrategy openStrategy, RefillStrategy refillStrategy) {
        super("BallPen", color, refill, openStrategy, refillStrategy);
    }

    @Override
    public void write(String text) {
        System.out.println("[BallPen] Writing reliably: " + text);
    }
}
