package slot;

import strategy.PricingStrategy;

public class MediumSlot extends Slot {
    public MediumSlot(int slotId, PricingStrategy pricingStrategy) {
        super(slotId, SlotType.MEDIUM, pricingStrategy);
    }
}
