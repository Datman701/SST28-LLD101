package entities;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    public String id;
    public List<Seat> seats = new ArrayList<>();

    public Screen(String id, List<Seat> seats) {
        this.id = id;
        if (seats != null) {
            this.seats.addAll(seats);
        }
    }
}
