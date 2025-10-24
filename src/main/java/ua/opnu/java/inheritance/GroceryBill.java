package ua.opnu.java.inheritance;
import java.util.ArrayList;
import java.util.List;
public class GroceryBill {
    private Employee clerk;
    private List<Item> items = new ArrayList<>();

    public GroceryBill(Employee clerk) {
        this.clerk = clerk;
    }

    public Employee getClerk() {
        return clerk;
    }

    public void add(Item i) {
        items.add(i);
    }

    public double getTotal() {
        double sum = 0;
        for (Item i : items) {
            sum += i.getPrice();
        }
        return sum;
    }

    protected List<Item> getItems() {
        return items;
    }
}
