package factory;

import model.Board;
import model.GameConfig;
import model.Jump;
import model.Ladder;
import model.Snake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BoardFactory {
    private final Random random;

    public BoardFactory() {
        this.random = new Random();
    }

    public BoardFactory(Random random) {
        this.random = random;
    }

    public Board create(GameConfig config) {
        int maxCell = config.maxCell();
        validateCapacity(config, maxCell);

        int attempts = 0;
        while (attempts < 2000) {
            Map<Integer, Jump> jumps = generateJumps(config, maxCell);
            if (isValid(jumps, config, maxCell)) {
                return new Board(maxCell, jumps);
            }
            attempts++;
        }

        throw new IllegalStateException("Could not generate a valid board after many attempts.");
    }

    private void validateCapacity(GameConfig config, int maxCell) {
        int availableStartCells = Math.max(0, (maxCell - 1) - 2 + 1); // cells 2..maxCell-1
        int requiredStartCells = config.getSnakeCount() + config.getLadderCount();
        if (requiredStartCells > availableStartCells) {
            throw new IllegalArgumentException("Not enough cells to place all snakes and ladders.");
        }
    }

    private Map<Integer, Jump> generateJumps(GameConfig config, int maxCell) {
        Map<Integer, Jump> jumps = new HashMap<>();
        placeSnakes(config.getSnakeCount(), maxCell, jumps);
        placeLadders(config.getLadderCount(), maxCell, jumps);
        return jumps;
    }

    private void placeSnakes(int count, int maxCell, Map<Integer, Jump> jumps) {
        int placed = 0;
        while (placed < count) {
            int head = randomInRange(2, maxCell - 1);
            if (jumps.containsKey(head)) {
                continue;
            }

            int tail = randomInRange(1, head - 1);
            jumps.put(head, new Snake(head, tail));
            placed++;
        }
    }

    private void placeLadders(int count, int maxCell, Map<Integer, Jump> jumps) {
        int placed = 0;
        while (placed < count) {
            int start = randomInRange(2, maxCell - 1);
            if (jumps.containsKey(start)) {
                continue;
            }

            int end = randomInRange(start + 1, maxCell);
            jumps.put(start, new Ladder(start, end));
            placed++;
        }
    }

    private int randomInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Invalid random range: min > max.");
        }
        return random.nextInt(max - min + 1) + min;
    }

    private boolean isValid(Map<Integer, Jump> jumps, GameConfig config, int maxCell) {
        if (jumps.size() != config.getSnakeCount() + config.getLadderCount()) {
            return false;
        }

        int snakeCounter = 0;
        int ladderCounter = 0;

        for (Map.Entry<Integer, Jump> entry : jumps.entrySet()) {
            int start = entry.getKey();
            Jump jump = entry.getValue();
            int end = jump.getEnd();

            if (start < 2 || start > maxCell - 1) {
                return false;
            }
            if (end < 1 || end > maxCell) {
                return false;
            }

            if (jump instanceof Snake) {
                snakeCounter++;
                if (start <= end) {
                    return false;
                }
            } else if (jump instanceof Ladder) {
                ladderCounter++;
                if (start >= end) {
                    return false;
                }
            } else {
                return false;
            }
        }

        if (snakeCounter != config.getSnakeCount() || ladderCounter != config.getLadderCount()) {
            return false;
        }

        return !hasCycle(jumps);
    }

    private boolean hasCycle(Map<Integer, Jump> jumps) {
        Map<Integer, Integer> state = new HashMap<>(); // 0=unvisited,1=visiting,2=done
        Set<Integer> starts = new HashSet<>(jumps.keySet());

        for (Integer start : starts) {
            if (state.getOrDefault(start, 0) == 0 && dfsCycle(start, jumps, state)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsCycle(int node, Map<Integer, Jump> jumps, Map<Integer, Integer> state) {
        state.put(node, 1);

        int next = jumps.get(node).getEnd();
        if (jumps.containsKey(next)) {
            int nextState = state.getOrDefault(next, 0);
            if (nextState == 1) {
                return true;
            }
            if (nextState == 0 && dfsCycle(next, jumps, state)) {
                return true;
            }
        }

        state.put(node, 2);
        return false;
    }
}
