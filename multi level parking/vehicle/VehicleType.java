package vehicle;

import slot.SlotType;

public enum VehicleType {
    BIKE,
    CAR,
    TRUCK;

    public boolean canParkIn(SlotType slotType) {
        return switch (this) {
            case BIKE -> slotType == SlotType.SMALL || slotType == SlotType.MEDIUM || slotType == SlotType.LARGE;
            case CAR -> slotType == SlotType.MEDIUM || slotType == SlotType.LARGE;
            case TRUCK -> slotType == SlotType.LARGE;
        };
    }
}
