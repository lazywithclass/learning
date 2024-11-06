package enums;

// credit https://www.baeldung.com/a-guide-to-java-enums#fields-methods-and-constructors-in-enums
public class Pizza {

    private PizzaStatus status;
    public enum PizzaStatus {
        ORDERED (5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY (2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered() {return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }
    }

    // Methods that set and get the status variable.
}
