import ua.opnu.java.inheritance.bill.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountBill2 {
    private final GroceryBill bill;
    private final boolean regularCustomer;
    private final List<Item> items = new ArrayList<>();

    public DiscountBill2(Employee clerk, boolean regularCustomer) {
        this.bill = new GroceryBill(clerk);
        this.regularCustomer = regularCustomer;
    }

    // --- helpers ---
    private static long toCents(double v) {
        return BigDecimal.valueOf(v).movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValue();
    }
    private static double centsToDouble(long cents) {
        BigDecimal bd = new BigDecimal(cents).movePointLeft(2);
        return Double.parseDouble(bd.toPlainString());
    }

    public Employee getClerk() { return bill.getClerk(); }

    public void add(Item i) {
        bill.add(i);
        items.add(i);
    }

    public double getTotal() {
        long sumCents = 0L;
        for (Item i : items) {
            long price = toCents(i.getPrice());
            long disc  = toCents(i.getDiscount());
            sumCents += regularCustomer ? (price - disc) : price;
        }
        return centsToDouble(sumCents);
    }

    public int getDiscountCount() {
        if (!regularCustomer) return 0;
        int c = 0;
        for (Item i : items) if (i.getDiscount() > 0) c++;
        return c;
    }

    public double getDiscountAmount() {
        if (!regularCustomer) return 0.0;
        long sum = 0L;
        for (Item i : items) sum += toCents(i.getDiscount());
        return centsToDouble(sum);
    }

    public double getDiscountPercent() {
        if (!regularCustomer) return 0.0;

        long fullCents = 0L, discCents = 0L;
        for (Item i : items) {
            fullCents += toCents(i.getPrice());
            discCents += toCents(i.getDiscount());
        }
        long withCents = fullCents - discCents;

        BigDecimal hundred = new BigDecimal("100");
        BigDecimal with    = new BigDecimal(withCents);
        BigDecimal full    = new BigDecimal(fullCents);

        BigDecimal pct = hundred.subtract(
                with.multiply(hundred).divide(full, 13, RoundingMode.HALF_UP)
        );

        return Double.parseDouble(pct.toPlainString());
    }
}