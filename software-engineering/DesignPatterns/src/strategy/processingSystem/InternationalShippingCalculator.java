package strategy.processingSystem;

public class InternationalShippingCalculator implements ShippingCalculator {

    @Override
    public double calculateShippingCost(Order order) {
        return order.weight() + 20.0;
    }
}
