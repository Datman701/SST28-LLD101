package slot;

import java.util.concurrent.atomic.AtomicBoolean;

import strategy.PricingStrategy;

public abstract class Slot {
    private final int slotId;
    private final SlotType slotType;
    private final PricingStrategy pricingStrategy;
    private final AtomicBoolean occupied;

    protected Slot(int slotId, SlotType slotType, PricingStrategy pricingStrategy) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.pricingStrategy = pricingStrategy;
        this.occupied = new AtomicBoolean(false);
    }

    public boolean tryReserve() {
        return occupied.compareAndSet(false, true);
    }

    public void release() {
        occupied.set(false);
    }

    public boolean isOccupied() {
        return occupied.get();
    }

    public SlotType getType() {
        return slotType;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public int getSlotId() {
        return slotId;
    }
}
