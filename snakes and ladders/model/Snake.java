package model;

public class Snake extends Jump {
    public Snake(int head, int tail) {
        super(head, tail);
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be greater than tail.");
        }
    }

    @Override
    public String type() {
        return "Snake";
    }
}
