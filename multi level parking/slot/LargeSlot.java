package slot;

import strategy.PricingStrategy;

public class LargeSlot extends Slot {
    public LargeSlot(int slotId, PricingStrategy pricingStrategy) {
        super(slotId, SlotType.LARGE, pricingStrategy);
    }
}
