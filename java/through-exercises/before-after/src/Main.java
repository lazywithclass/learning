import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().solve(new int[]{0, -3, 5, -4, -2, 3, 1, 0}));
        System.out.println(new Main().solveBetter(new int[]{0, -3, 5, -4, -2, 3, 1, 0}));
    }

    public List<Integer> solveBetter(int[] numbers) {
        List<Integer> solution = new ArrayList<>();
        int[] before = new int[numbers.length];
        int runningTotal = 0;
        for (int i = 0; i < numbers.length; i++) {
            before[i] = runningTotal;
            runningTotal += numbers[i];
        }

        runningTotal = 0;
        if (runningTotal == before[numbers.length - 1]) {
            solution.add(numbers.length-1);
        }
        for (int i = numbers.length - 2; i >= 0; i--) {
            runningTotal += numbers[i + 1];
            if (runningTotal == before[i]) {
                solution.add(i);
            }
        }

        return solution;
    }

    public List<Integer> solve(int[] numbers) {
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            int before = 0;
            int after = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (i == j) {
                    continue;
                }

                if (i < j) {
                    before += numbers[j];
                }
                if (i > j) {
                    after += numbers[j];
                }
            }

            if (before == after) {
                solution.add(i);
            }
        }
        return solution;
    }
}