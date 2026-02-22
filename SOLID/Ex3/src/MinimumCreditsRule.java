public class MinimumCreditsRule implements EligibilityRule {
    private final int minCredits;

    public MinimumCreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public boolean isViolated(StudentProfile profile) {
        return profile.earnedCredits < minCredits;
    }

    @Override
    public String getReason() {
        return "credits below " + minCredits;
    }
}
