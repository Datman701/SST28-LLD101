public class MinimumAttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public MinimumAttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public boolean isViolated(StudentProfile profile) {
        return profile.attendancePct < minAttendance;
    }

    @Override
    public String getReason() {
        return "attendance below " + minAttendance;
    }
}
