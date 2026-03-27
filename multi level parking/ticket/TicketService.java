package ticket;

import java.util.UUID;

import slot.Slot;
import vehicle.Vehicle;

public class TicketService {
    public Ticket openTicket(Vehicle vehicle, Slot slot) {
        return new Ticket(
                UUID.randomUUID().toString(),
                slot,
                vehicle,
                System.currentTimeMillis(),
                slot.getPricingStrategy()
        );
    }

    public double closeTicket(Ticket ticket) {
        double amount = ticket.calculatePrice();
        ticket.getSlot().release();
        return amount;
    }
}
