package model;

public class Player {
    private final String id;
    private final String name;
    private int position;
    private boolean finished;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0;
        this.finished = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void moveTo(int newPosition) {
        this.position = newPosition;
    }

    public boolean isFinished() {
        return finished;
    }

    public void markFinished() {
        this.finished = true;
    }
}
