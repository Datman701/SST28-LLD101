package services;

import entities.Payment;
import java.util.UUID;

public class PaymentService {
    public Payment pay(double amount) {
        return new Payment(UUID.randomUUID().toString(), amount, "SUCCESS");
    }
}
