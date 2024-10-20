package it.unimi.di.sweng.lab03;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntBiFunction;

public class ForthInterpreter implements Interpreter {

    private static final Map<String, ToIntBiFunction<Integer, Integer>> APPLICATIONS = Map.ofEntries(
            Map.entry("+", Integer::sum),
            Map.entry("-", (a, b) -> a - b),
            Map.entry("*", (a, b) -> a * b),
            Map.entry("/", (a, b) -> a / b)
    );

    private final Deque<Integer> stack;

    public ForthInterpreter() {
        this.stack = new ArrayDeque<>();
    }


    @Override
    public void input(String program) {
        if (program.isBlank()) return;

        String[] split = program.split("\\s+");
        for (String token : split) {
            if (token.contains("+") && token.length() > 1) throw new IllegalArgumentException("Token error: '" + token + "'");

            try {
                if (APPLICATIONS.containsKey(token)) {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Stack Underflow");
                    }

                    var appl = APPLICATIONS.get(token);
                    Integer op1 = stack.pop();
                    Integer op2 = stack.pop();
                    stack.add(appl.applyAsInt(op1, op2));
                } else {
                    stack.add(Integer.parseInt(token));
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Token error: '"+ token +"'");
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : this.stack)
            sb.append(i).append(" ");

        return sb.append("<- Top").toString();
    }
}
