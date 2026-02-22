public class StandardTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(String customerType, double subtotal) {
        double taxPct = getTaxPercent(customerType);
        return subtotal * (taxPct / 100.0);
    }

    @Override
    public double getTaxPercent(String customerType) {
        return TaxRules.taxPercent(customerType);
    }
}
