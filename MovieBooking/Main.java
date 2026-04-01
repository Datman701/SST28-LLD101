import entities.Booking;
import entities.City;
import entities.Movie;
import entities.Screen;
import entities.Seat;
import entities.Show;
import entities.Theatre;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import services.AdminService;
import services.BookingService;
import services.PaymentService;
import services.PricingService;
import services.SeatLockService;
import services.UserService;

public class Main {
	public static void main(String[] args) {
		AdminService adminService = new AdminService();
		SeatLockService seatLockService = new SeatLockService();
		PricingService pricingService = new PricingService();
		PaymentService paymentService = new PaymentService();
		BookingService bookingService = new BookingService();

		UserService userService = new UserService(
			adminService,
			seatLockService,
			pricingService,
			paymentService,
			bookingService
		);

		City city = new City("c1", "Bengaluru");
		adminService.addCity(city);

		List<Seat> seats = Arrays.asList(
			new Seat("S1", 1, 1, "REGULAR", 150),
			new Seat("S2", 1, 2, "REGULAR", 150),
			new Seat("S3", 1, 3, "PREMIUM", 250)
		);
		Screen screen = new Screen("screen-1", seats);
		Theatre theatre = new Theatre("t1", "PVR Orion", city);
		theatre.screens.add(screen);
		adminService.addTheatre(theatre);

		Movie movie = new Movie("m1", "Inception", 148);
		adminService.addMovie(movie);

		Show show = new Show("show-1", movie, screen, LocalDateTime.now().plusHours(2));
		adminService.addShow(show);

		System.out.println("Movies in city:");
		userService.showMovies(city).forEach(m -> System.out.println("- " + m.title));

		System.out.println("Theatres in city:");
		userService.showTheatres(city).forEach(t -> System.out.println("- " + t.name));

		Booking booking = userService.bookTicket("show-1", Arrays.asList(seats.get(0), seats.get(1)));
		System.out.println("Booking confirmed: " + booking.id + " amount=" + booking.totalAmount);

		try {
			userService.bookTicket("show-1", Arrays.asList(seats.get(0)));
		} catch (Exception e) {
			System.out.println("Second booking failed as expected: " + e.getMessage());
		}
	}
}
