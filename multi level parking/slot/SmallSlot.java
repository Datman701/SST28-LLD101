package slot;

import strategy.PricingStrategy;

public class SmallSlot extends Slot {
    public SmallSlot(int slotId, PricingStrategy pricingStrategy) {
        super(slotId, SlotType.SMALL, pricingStrategy);
    }
}
