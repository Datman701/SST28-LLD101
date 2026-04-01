package entities;

public class Seat {
    public String id;
    public int row;
    public int number;
    public String type;
    public double price;

    public Seat(String id, int row, int number, String type, double price) {
        this.id = id;
        this.row = row;
        this.number = number;
        this.type = type;
        this.price = price;
    }
}
