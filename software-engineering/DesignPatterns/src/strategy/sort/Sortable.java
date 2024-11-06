package strategy.sort;

import java.util.List;

public interface Sortable<T> {
    List<T> sort(List<T> unsorted);
}
