import java.util.List;

import gate.EntryGate;
import parking.Level;
import parking.ParkingLot;
import slot.LargeSlot;
import slot.MediumSlot;
import slot.SmallSlot;
import slot.Slot;
import strategy.DefaultPricingStrategy;
import strategy.NearestParkingStrategy;
import strategy.ParkingStrategy;
import ticket.Ticket;
import ticket.TicketService;
import vehicle.Vehicle;
import vehicle.VehicleType;

public class Main {
    public static void main(String[] args) {
        DefaultPricingStrategy pricingStrategy = new DefaultPricingStrategy();

        Level level1 = new Level(1, List.of(
                new SmallSlot(1, pricingStrategy),
                new MediumSlot(2, pricingStrategy),
                new LargeSlot(3, pricingStrategy)
        ));

        Level level2 = new Level(2, List.of(
                new SmallSlot(1, pricingStrategy),
                new MediumSlot(2, pricingStrategy),
                new LargeSlot(3, pricingStrategy)
        ));

        List<Level> levels = List.of(level1, level2);
        ParkingStrategy parkingStrategy = new NearestParkingStrategy();
        TicketService ticketService = new TicketService();
        ParkingLot parkingLot = new ParkingLot(levels, parkingStrategy, ticketService);

        EntryGate gate1 = new EntryGate(1);

        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);
        Vehicle bike = new Vehicle("KA01XY1111", VehicleType.BIKE);

        Ticket ticket1 = parkingLot.park(car, gate1);
        Ticket ticket2 = parkingLot.park(bike, gate1);

        if (ticket1 != null) {
            System.out.println("Allocated slot: " + formatSlot(levels, ticket1.getSlot()));
        }
        if (ticket2 != null) {
            System.out.println("Allocated slot: " + formatSlot(levels, ticket2.getSlot()));
        }

        if (ticket1 != null) {
            double amount = parkingLot.exit(ticket1);
            System.out.println("Price: " + amount);
        }
    }

    private static String formatSlot(List<Level> levels, Slot slot) {
        for (Level level : levels) {
            for (Slot levelSlot : level.getSlots()) {
                if (levelSlot == slot) {
                    return "L" + level.getLevelId() + "-S" + slot.getSlotId();
                }
            }
        }
        return "S" + slot.getSlotId();
    }
}
