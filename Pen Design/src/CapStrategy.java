public class CapStrategy implements OpenStrategy {
    @Override
    public void open() {
        System.out.println("Opening pen by removing cap.");
    }

    @Override
    public void close() {
        System.out.println("Closing pen by putting cap back.");
    }
}
