package strategy.ducks;

public class MallardDuck extends Duck {

    public MallardDuck() {
        // the Mallard duck is chatty
        this.quackBehaviour = new Quack();
    }

    @Override
    public void display() {
        System.out.println("this is the Mallard duck");
    }
}
