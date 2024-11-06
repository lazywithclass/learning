package iterator;

public class Main {

    public static void main(String[] args) {
        Cards cards = new Cards();
        for (Card card : cards) {
            System.out.println(card);
        }
    }

}
