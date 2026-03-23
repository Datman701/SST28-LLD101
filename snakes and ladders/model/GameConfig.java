package model;

public class GameConfig {
    private final int n;
    private final int snakeCount;
    private final int ladderCount;

    public GameConfig(int n, int snakeCount, int ladderCount) {
        if (n < 3) {
            throw new IllegalArgumentException("n must be at least 3 for a playable board.");
        }
        this.n = n;
        this.snakeCount = snakeCount;
        this.ladderCount = ladderCount;
    }

    public int getN() {
        return n;
    }

    public int getSnakeCount() {
        return snakeCount;
    }

    public int getLadderCount() {
        return ladderCount;
    }

    public int maxCell() {
        return n * n;
    }
}
