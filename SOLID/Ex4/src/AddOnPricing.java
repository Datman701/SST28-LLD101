public class AddOnPricing implements PricingComponent {
    private final AddOn addOn;

    public AddOnPricing(AddOn addOn) {
        this.addOn = addOn;
    }

    @Override
    public Money getMonthlyFee() {
        double fee = switch (addOn) {
            case MESS -> 1000.0;
            case LAUNDRY -> 500.0;
            case GYM -> 300.0;
        };
        return new Money(fee);
    }
}
