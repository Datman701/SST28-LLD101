package entities;

public class Payment {
    public String id;
    public double amount;
    public String status;

    public Payment(String id, double amount, String status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }
}
