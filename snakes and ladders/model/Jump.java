package model;

public abstract class Jump {
    protected final int start;
    protected final int end;

    protected Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int apply(int position) {
        return position == start ? end : position;
    }

    public abstract String type();
}
