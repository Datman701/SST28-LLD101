public class BallRefillStrategy implements RefillStrategy {
    @Override
    public void refill(Refill refill) {
        System.out.println("Ball refill: oil-based " + refill.getColor() + " ink cartridge replaced.");
    }
}
