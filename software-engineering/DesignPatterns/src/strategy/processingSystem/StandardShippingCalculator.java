package strategy.processingSystem;

public class StandardShippingCalculator implements ShippingCalculator {

    @Override
    public double calculateShippingCost(Order order) {
        return order.weight() + 5.0;
    }
}
