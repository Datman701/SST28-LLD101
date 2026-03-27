package parking;

import java.util.ArrayList;
import java.util.List;

import slot.Slot;

public class Level {
    private final int levelId;
    private final List<Slot> slots;

    public Level(int levelId, List<Slot> slots) {
        this.levelId = levelId;
        this.slots = slots;
    }

    public int getLevelId() {
        return levelId;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public List<Slot> getAvailableSlots() {
        List<Slot> available = new ArrayList<>();
        for (Slot slot : slots) {
            if (!slot.isOccupied()) {
                available.add(slot);
            }
        }
        return available;
    }
}
