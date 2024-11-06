package strategy.sort;

import java.util.List;

public class Client<T> {

    private final Sortable<T> sort;

    public Client(Sortable<T> sort) {
        this.sort = sort;
    }

    public List<T> prepare(List<T> unsorted) {
        return sort.sort(unsorted);
    }
}
