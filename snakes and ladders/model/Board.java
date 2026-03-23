package model;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final int maxCell;
    private final Map<Integer, Jump> jumpsByStart;

    public Board(int maxCell, Map<Integer, Jump> jumpsByStart) {
        this.maxCell = maxCell;
        this.jumpsByStart = Collections.unmodifiableMap(jumpsByStart);
    }

    public int getMaxCell() {
        return maxCell;
    }

    public boolean hasJumpAt(int cell) {
        return jumpsByStart.containsKey(cell);
    }

    public Jump getJumpAt(int cell) {
        return jumpsByStart.get(cell);
    }

    public Map<Integer, Jump> getAllJumps() {
        return jumpsByStart;
    }

    public int resolvePosition(int position) {
        int current = position;
        int safetyCounter = 0;

        while (jumpsByStart.containsKey(current)) {
            current = jumpsByStart.get(current).apply(current);
            safetyCounter++;
            if (safetyCounter > maxCell) {
                throw new IllegalStateException("Jump cycle detected during resolution.");
            }
        }
        return current;
    }
}
