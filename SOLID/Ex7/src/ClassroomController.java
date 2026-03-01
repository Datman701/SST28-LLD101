public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // Use capability-based approach
        Projector pj = (Projector) reg.getFirstOfType("Projector");
        pj.powerOn();
        pj.connectInput("HDMI-1");

        BrightnessControllable lights = (BrightnessControllable) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        TemperatureControllable ac = (TemperatureControllable) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        AttendanceCapable scan = (AttendanceCapable) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Powerable) reg.getFirstOfType("Projector")).powerOff();
        ((Powerable) reg.getFirstOfType("LightsPanel")).powerOff();
        ((Powerable) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
