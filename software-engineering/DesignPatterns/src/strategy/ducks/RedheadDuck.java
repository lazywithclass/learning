package strategy.ducks;

public class RedheadDuck extends Duck {

    public RedheadDuck() {
        // silent is, the read headed duck
        this.quackBehaviour = new Mute();
    }

    @Override
    public void display() {
        System.out.println("this is the red headed duck");
    }
}
