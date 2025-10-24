package ua.opnu.java.inheritance;

public class Item {
    private String name;
    private double price;
    private double discount; // абсолютна знижка, а не %

    public Item(String name, double price, double discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return name + " " + price + " (" + discount + ")";
    }
}
