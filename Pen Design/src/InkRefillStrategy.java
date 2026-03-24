public class InkRefillStrategy implements RefillStrategy {
    @Override
    public void refill(Refill refill) {
        System.out.println("Ink refill: liquid " + refill.getColor() + " ink filled.");
    }
}
