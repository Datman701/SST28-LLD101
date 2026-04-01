package services;

import entities.Booking;
import entities.City;
import entities.Movie;
import entities.Payment;
import entities.Seat;
import entities.Show;
import entities.Theatre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserService {
    private final AdminService adminService;
    private final SeatLockService seatLockService;
    private final PricingService pricingService;
    private final PaymentService paymentService;
    private final BookingService bookingService;

    public UserService(
        AdminService adminService,
        SeatLockService seatLockService,
        PricingService pricingService,
        PaymentService paymentService,
        BookingService bookingService
    ) {
        this.adminService = adminService;
        this.seatLockService = seatLockService;
        this.pricingService = pricingService;
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    public List<Movie> showMovies(City city) {
        Set<String> seenMovieIds = new HashSet<>();
        List<Movie> movies = new ArrayList<>();
        for (Show show : adminService.getShows()) {
            if (showInCity(show, city) && seenMovieIds.add(show.movie.id)) {
                movies.add(show.movie);
            }
        }
        return movies;
    }

    public List<Theatre> showTheatres(City city) {
        return city.theatres;
    }

    public Booking bookTicket(String showId, List<Seat> requestedSeats) {
        Show show = findShow(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found: " + showId);
        }

        List<Seat> seats = normalizeSeats(show, requestedSeats);
        List<Seat> availableSeats = getAvailableSeats(show);
        if (!availableSeats.containsAll(seats)) {
            throw new IllegalStateException("Some seats are not available");
        }

        boolean locked = seatLockService.lockSeats(show, seats);
        if (!locked) {
            throw new IllegalStateException("Unable to lock seats");
        }

        try {
            double amount = pricingService.calculatePrice(show, seats);
            Payment payment = paymentService.pay(amount);
            if (!"SUCCESS".equals(payment.status)) {
                throw new IllegalStateException("Payment failed");
            }
            return bookingService.createBooking(show, seats, amount);
        } finally {
            // Seats must be released after booking flow completion.
            seatLockService.unlockSeats(show, seats);
        }
    }

    private Show findShow(String showId) {
        for (Show show : adminService.getShows()) {
            if (show.id.equals(showId)) {
                return show;
            }
        }
        return null;
    }

    private boolean showInCity(Show show, City city) {
        for (Theatre theatre : city.theatres) {
            if (theatre.screens.contains(show.screen)) {
                return true;
            }
        }
        return false;
    }

    private List<Seat> normalizeSeats(Show show, List<Seat> requestedSeats) {
        Map<String, Seat> byId = new HashMap<>();
        for (Seat seat : show.screen.seats) {
            byId.put(seat.id, seat);
        }

        List<Seat> result = new ArrayList<>();
        for (Seat seat : requestedSeats) {
            Seat actual = byId.get(seat.id);
            if (actual == null) {
                throw new IllegalArgumentException("Seat not part of show screen: " + seat.id);
            }
            result.add(actual);
        }
        return result;
    }

    private List<Seat> getAvailableSeats(Show show) {
        Set<String> unavailableIds = new HashSet<>();
        for (Seat seat : seatLockService.getLockedSeats(show)) {
            unavailableIds.add(seat.id);
        }
        for (Seat seat : bookingService.getBookedSeats(show)) {
            unavailableIds.add(seat.id);
        }

        List<Seat> available = new ArrayList<>();
        for (Seat seat : show.screen.seats) {
            if (!unavailableIds.contains(seat.id)) {
                available.add(seat);
            }
        }
        return available;
    }
}
