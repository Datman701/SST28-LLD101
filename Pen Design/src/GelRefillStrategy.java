public class GelRefillStrategy implements RefillStrategy {
    @Override
    public void refill(Refill refill) {
        System.out.println("Gel refill: smooth " + refill.getColor() + " gel ink loaded.");
    }
}
