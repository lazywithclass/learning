package enums;

public class Main {
    public static void main(String[] args) {
        Pizza.PizzaStatus ordered = Pizza.PizzaStatus.ORDERED;
        System.out.println(ordered.getTimeToDelivery());
    }
}
