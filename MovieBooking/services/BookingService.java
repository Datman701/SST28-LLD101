package services;

import entities.Booking;
import entities.Seat;
import entities.Show;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();

    public Booking createBooking(Show show, List<Seat> seats, double amount) {
        Booking booking = new Booking(UUID.randomUUID().toString(), show, seats, amount);
        bookings.add(booking);
        return booking;
    }

    public List<Seat> getBookedSeats(Show show) {
        List<Seat> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.show.id.equals(show.id)) {
                result.addAll(booking.seats);
            }
        }
        return result;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
