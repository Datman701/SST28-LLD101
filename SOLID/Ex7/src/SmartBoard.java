public class SmartBoard implements Powerable, BrightnessControllable, InputConnectable {
    private boolean on;

    @Override public void powerOn() { on = true; System.out.println("SmartBoard ON"); }
    @Override public void powerOff() { on = false; System.out.println("SmartBoard OFF"); }

    @Override public void setBrightness(int pct) { System.out.println("SmartBoard brightness: " + pct + "%"); }
    @Override public void connectInput(String port) { if (on) System.out.println("SmartBoard connected to " + port); }
}
