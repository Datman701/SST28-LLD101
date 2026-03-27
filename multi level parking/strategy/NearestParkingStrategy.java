package strategy;

import java.util.List;

import gate.EntryGate;
import parking.Level;
import slot.Slot;
import vehicle.Vehicle;

public class NearestParkingStrategy implements ParkingStrategy {
    @Override
    public Slot getSlot(List<Level> levels, Vehicle vehicle, EntryGate gate) {
        for (Level level : levels) {
            for (Slot slot : level.getSlots()) {
                if (!slot.isOccupied() && vehicle.getType().canParkIn(slot.getType())) {
                    return slot;
                }
            }
        }
        return null;
    }
}
