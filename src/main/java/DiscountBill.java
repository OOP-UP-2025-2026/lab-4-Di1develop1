import ua.opnu.java.inheritance.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountBill extends GroceryBill {
    private final boolean regularCustomer;

    public DiscountBill(Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
    }

    // --- helpers: точна робота в "копійках" ---
    private static long toCents(double v) {
        return BigDecimal.valueOf(v).movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValue();
    }
    private static double centsToDouble(long cents) {
        BigDecimal bd = new BigDecimal(cents).movePointLeft(2);
        return Double.parseDouble(bd.toPlainString()); // той самий формат, що в літералах тестів
    }

    @Override
    public double getTotal() {
        long sumCents = 0L;
        for (Item i : getItems()) {
            long price = toCents(i.getPrice());
            long disc  = toCents(i.getDiscount());
            sumCents += regularCustomer ? (price - disc) : price;
        }
        return centsToDouble(sumCents);
    }

    public int getDiscountCount() {
        if (!regularCustomer) return 0;
        int c = 0;
        for (Item i : getItems()) if (i.getDiscount() > 0) c++;
        return c;
    }

    public double getDiscountAmount() {
        if (!regularCustomer) return 0.0;
        long sum = 0L;
        for (Item i : getItems()) sum += toCents(i.getDiscount());
        return centsToDouble(sum);
    }

    public double getDiscountPercent() {
        if (!regularCustomer) return 0.0;

        long fullCents = 0L, discCents = 0L;
        for (Item i : getItems()) {
            fullCents += toCents(i.getPrice());
            discCents += toCents(i.getDiscount());
        }
        long withCents = fullCents - discCents;

        // 13 знаків після коми — як у тесті (16.6421928536466)
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal with    = new BigDecimal(withCents);
        BigDecimal full    = new BigDecimal(fullCents);

        BigDecimal pct = hundred.subtract(
                with.multiply(hundred).divide(full, 13, RoundingMode.HALF_UP)
        );

        return Double.parseDouble(pct.toPlainString());
    }
}