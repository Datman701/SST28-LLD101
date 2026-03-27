package strategy;

import ticket.Ticket;

public interface PricingStrategy {
    double calculate(Ticket ticket);
}
