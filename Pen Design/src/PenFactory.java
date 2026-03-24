public class PenFactory {
    public Pen getPen(String penType, String color, String inkType, RefillStrategy refillStrategy) {
        if (penType == null || penType.trim().isEmpty()) {
            throw new IllegalArgumentException("penType is required");
        }

        Refill refill = new Refill(color, inkType);
        String normalizedPenType = penType.toLowerCase();
        RefillStrategy effectiveRefillStrategy = refillStrategy != null
                ? refillStrategy
                : defaultRefillStrategyFor(normalizedPenType);

        switch (normalizedPenType) {
            case "gel":
                return new GelPen(color, refill, new CapStrategy(), effectiveRefillStrategy);
            case "ink":
                return new InkPen(color, refill, new CapStrategy(), effectiveRefillStrategy);
            case "ball":
                return new BallPen(color, refill, new ClickStrategy(), effectiveRefillStrategy);
            default:
                throw new IllegalArgumentException("Unknown pen type: " + penType);
        }
    }

    private RefillStrategy defaultRefillStrategyFor(String penType) {
        switch (penType) {
            case "gel":
                return new GelRefillStrategy();
            case "ink":
                return new InkRefillStrategy();
            case "ball":
                return new BallRefillStrategy();
            default:
                throw new IllegalArgumentException("Unknown pen type: " + penType);
        }
    }
}
