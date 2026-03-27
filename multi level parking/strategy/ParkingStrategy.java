package strategy;

import java.util.List;

import gate.EntryGate;
import parking.Level;
import slot.Slot;
import vehicle.Vehicle;

public interface ParkingStrategy {
    Slot getSlot(List<Level> levels, Vehicle vehicle, EntryGate gate);
}
