package strategy.sort;

import java.util.List;

public class MergeSort<T> implements Sortable<T> {

    @Override
    public List<T> sort(List<T> unsorted) {
        System.out.println("mergesort");
        return unsorted;
    }
}
