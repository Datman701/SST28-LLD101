public class Main {
    public static void main(String[] args) {
        PenFactory factory = new PenFactory();

        System.out.println("=== Default refill strategies by pen type ===");
        Pen gelPen = factory.getPen("gel", "Blue", "Gel", null);
        Pen inkPen = factory.getPen("ink", "Black", "Liquid", null);
        Pen ballPen = factory.getPen("ball", "Red", "Oil", null);

        runDemo(gelPen, "Hello from gel pen");
        runDemo(inkPen, "Hello from ink pen");
        runDemo(ballPen, "Hello from ball pen");

        System.out.println("\n=== User-injected refill strategy override ===");
        RefillStrategy customStrategy = new RefillStrategy() {
            @Override
            public void refill(Refill refill) {
                System.out.println("Custom refill: user selected strategy for "
                        + refill.getColor() + " " + refill.getInkType() + " ink.");
            }
        };

        Pen customGelPen = factory.getPen("gel", "Green", "Gel", customStrategy);
        runDemo(customGelPen, "Hello from custom strategy gel pen");
    }

    private static void runDemo(Pen pen, String text) {
        System.out.println("\n--- " + pen.getType() + " Demo ---");
        pen.open();
        pen.write(text);
        pen.refill();
        pen.close();
    }
}
