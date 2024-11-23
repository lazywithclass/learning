package strategy.processingSystem;

public class ExpressShippingCalculator implements ShippingCalculator {

    @Override
    public double calculateShippingCost(Order order) {
        return order.weight() + 10.0;
    }
}
