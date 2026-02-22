public class MinimumCgrRule implements EligibilityRule {
    private final double minCgr;

    public MinimumCgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public boolean isViolated(StudentProfile profile) {
        return profile.cgr < minCgr;
    }

    @Override
    public String getReason() {
        return "CGR below " + minCgr;
    }
}
