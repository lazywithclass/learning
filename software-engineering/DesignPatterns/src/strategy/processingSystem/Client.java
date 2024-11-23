package strategy.processingSystem;

public class Client {

    private ShippingCalculator shippingCalculator;

    public Client(ShippingCalculator shippingCalculator) {
        this.shippingCalculator = shippingCalculator;
    }

    public double calculateShippingCost(Order order) {
        return shippingCalculator.calculateShippingCost(order);
    }
}
