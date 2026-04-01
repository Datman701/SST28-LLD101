package entities;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    public String id;
    public Show show;
    public List<Seat> seats = new ArrayList<>();
    public double totalAmount;

    public Booking(String id, Show show, List<Seat> seats, double totalAmount) {
        this.id = id;
        this.show = show;
        if (seats != null) {
            this.seats.addAll(seats);
        }
        this.totalAmount = totalAmount;
    }
}
