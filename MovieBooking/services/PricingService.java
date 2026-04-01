package services;

import entities.Seat;
import entities.Show;
import java.util.List;

public class PricingService {
    public double calculatePrice(Show show, List<Seat> seats) {
        double total = 0.0;
        for (Seat seat : seats) {
            total += seat.price;
        }
        return total;
    }
}
