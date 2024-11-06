package strategy.ducks;

public abstract class Duck {

    protected QuackBehaviour quackBehaviour;

    public void performQuack() {
        quackBehaviour.quack();
    }

    public void swim() {}

    public abstract void display();
}
