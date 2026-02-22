import java.util.List;

public interface DiscountCalculator {
    double calculateDiscount(String customerType, double subtotal, int distinctLines);
}
