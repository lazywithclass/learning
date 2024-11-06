package strategy.ducks;

public class Quack implements QuackBehaviour {
    @Override
    public void quack() {
        System.out.println("quack");
    }
}
