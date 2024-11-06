package strategy.sort;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var client = new Client<>(new MergeSort<>());
        client.prepare(List.of(1,8,5,3,4,2,6,10,9));
    }
}
