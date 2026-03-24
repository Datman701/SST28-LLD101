public class ClickStrategy implements OpenStrategy {
    @Override
    public void open() {
        System.out.println("Opening pen by clicking tip out.");
    }

    @Override
    public void close() {
        System.out.println("Closing pen by clicking tip in.");
    }
}
