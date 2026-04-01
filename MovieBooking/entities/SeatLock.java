package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeatLock {
    public Show show;
    public List<Seat> seats = new ArrayList<>();
    public LocalDateTime expiryTime;

    public SeatLock(Show show, List<Seat> seats, LocalDateTime expiryTime) {
        this.show = show;
        if (seats != null) {
            this.seats.addAll(seats);
        }
        this.expiryTime = expiryTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}
