package parking;

import java.util.List;

import gate.EntryGate;
import slot.Slot;
import strategy.ParkingStrategy;
import ticket.Ticket;
import ticket.TicketService;
import vehicle.Vehicle;

public class ParkingLot {
    private final List<Level> levels;
    private final ParkingStrategy parkingStrategy;
    private final TicketService ticketService;

    public ParkingLot(List<Level> levels, ParkingStrategy parkingStrategy, TicketService ticketService) {
        this.levels = levels;
        this.parkingStrategy = parkingStrategy;
        this.ticketService = ticketService;
    }

    public Ticket park(Vehicle vehicle, EntryGate gate) {
        while (true) {
            Slot slot = parkingStrategy.getSlot(levels, vehicle, gate);
            if (slot == null) {
                return null;
            }
            if (slot.tryReserve()) {
                return ticketService.openTicket(vehicle, slot);
            }
        }
    }

    public double exit(Ticket ticket) {
        return ticketService.closeTicket(ticket);
    }
}
