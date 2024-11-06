package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card> {

    private List<Card> cards = new ArrayList<>();

    @Override
    public Iterator iterator() {
        return cards.iterator();
    }
}
