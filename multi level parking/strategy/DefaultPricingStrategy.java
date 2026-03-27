package strategy;

import slot.SlotType;
import ticket.Ticket;

public class DefaultPricingStrategy implements PricingStrategy {
    private static final long HOUR_MILLIS = 60L * 60L * 1000L;

    @Override
    public double calculate(Ticket ticket) {
        long now = System.currentTimeMillis();
        long duration = Math.max(0L, now - ticket.getEntryTime());
        long hours = (duration + HOUR_MILLIS - 1L) / HOUR_MILLIS;
        if (hours == 0L) {
            hours = 1L;
        }

        return hours * getRate(ticket.getSlot().getType());
    }

    private int getRate(SlotType slotType) {
        return switch (slotType) {
            case SMALL -> 10;
            case MEDIUM -> 20;
            case LARGE -> 30;
        };
    }
}
