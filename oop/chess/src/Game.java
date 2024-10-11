import strategies.RandomStrategy;
import strategies.Strategy;

public class Game {

    private Player one;
    private Player two;
    private Player next;

    public void play() {
        Strategy strategy = new RandomStrategy();

        one = new Player("one", strategy);
        two = new Player("two", strategy);

        print();
        while(isRunning()) {
            playNext();
            print();
        }

        printEnd();
    }

    private void playNext() {
        if (next == null) {
            next = one;
        }

        next.play();
        next = next == one ? two : one;
    }

    private boolean isRunning() {
        return next.hasAMove();
    }

    private void print() {
        
    }

    private void printEnd() {
    }


}
