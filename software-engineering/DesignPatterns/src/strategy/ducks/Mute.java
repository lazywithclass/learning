package strategy.ducks;

public class Mute implements QuackBehaviour {

    @Override
    public void quack() {
        System.out.println("*silent*");
    }
}
