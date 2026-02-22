import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStorage storage;
    private final TaxCalculator taxCalculator;
    private final DiscountCalculator discountCalculator;
    private final PricingCalculator pricingCalculator;
    private final InvoiceBuilder invoiceBuilder;
    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.storage = new FileStore();
        this.taxCalculator = new StandardTaxCalculator();
        this.discountCalculator = new StandardDiscountCalculator();
        this.pricingCalculator = new PricingCalculator(menu);
        this.invoiceBuilder = new InvoiceBuilder(pricingCalculator);
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        // Calculate pricing
        double subtotal = pricingCalculator.calculateSubtotal(lines);

        // Calculate tax
        double taxPct = taxCalculator.getTaxPercent(customerType);
        double tax = taxCalculator.calculateTax(customerType, subtotal);

        // Calculate discount
        double discount = discountCalculator.calculateDiscount(customerType, subtotal, lines.size());

        // Calculate total
        double total = subtotal + tax - discount;

        // Build invoice
        String invoice = invoiceBuilder.buildInvoice(invId, lines, subtotal, taxPct, tax, discount, total);

        // Format and print
        String printable = InvoiceFormatter.identityFormat(invoice);
        System.out.print(printable);

        // Save to storage
        storage.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + storage.countLines(invId) + ")");
    }
}
