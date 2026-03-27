package ticket;

import strategy.PricingStrategy;
import slot.Slot;
import vehicle.Vehicle;

public class Ticket {
    private final String id;
    private final Slot slot;
    private final Vehicle vehicle;
    private final long entryTime;
    private final PricingStrategy pricingStrategy;

    public Ticket(String id, Slot slot, Vehicle vehicle, long entryTime, PricingStrategy pricingStrategy) {
        this.id = id;
        this.slot = slot;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice() {
        return pricingStrategy.calculate(this);
    }

    public String getId() {
        return id;
    }

    public Slot getSlot() {
        return slot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
