public class RoomTypePricing implements PricingComponent {
    private final int roomType;

    public RoomTypePricing(int roomType) {
        this.roomType = roomType;
    }

    @Override
    public Money getMonthlyFee() {
        double base = switch (roomType) {
            case LegacyRoomTypes.SINGLE -> 14000.0;
            case LegacyRoomTypes.DOUBLE -> 15000.0;
            case LegacyRoomTypes.TRIPLE -> 12000.0;
            default -> 16000.0;
        };
        return new Money(base);
    }
}
