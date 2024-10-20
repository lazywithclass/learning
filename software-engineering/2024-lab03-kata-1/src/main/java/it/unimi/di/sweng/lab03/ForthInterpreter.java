package it.unimi.di.sweng.lab03;

import java.util.*;
import java.util.function.BiFunction;

public class ForthInterpreter implements Interpreter {

    private final Deque<Integer> stack = new ArrayDeque<>();

    Map<String, BiFunction<Integer, Integer, Integer>> algebraicOps = Map.of(
            "+", Integer::sum,
            "-", (Integer n, Integer m) -> n - m,
            "*", (Integer n, Integer m) -> n * m,
            "/", (Integer n, Integer m) -> n / m
    );

    Map<String, Runnable> stackOps = Map.of(
            "dup", () -> stack.push(stack.peek()),
            "swap", () -> {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Stack Underflow");
                }
                Integer n = stack.pop();
                Integer m = stack.pop();
                stack.push(n);
                stack.push(m);
            },
            "drop", stack::pop
    );

    @Override
    public void input(String program) {
        if (program.isBlank()) {
            return;
        }

        Map<String, String> words = extractWords(program);
        for (Map.Entry<String, String> word : words.entrySet()) {
            program = program.replaceAll(word.getKey(), word.getValue());
        }

        program = program.replaceAll(":.*; ", "");

        for (String token : program.split("\\s+")) {
            BiFunction<Integer, Integer, Integer> algebraicOp = algebraicOps.get(token);
            Runnable stackOp = stackOps.get(token);

            if (stackOp != null) {
                stackOp.run();
            } else if (algebraicOp != null) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Stack Underflow");
                }
                Integer arg1 = stack.pop();
                Integer arg2 = stack.pop();
                stack.push(algebraicOp.apply(arg2, arg1));
            } else {
                if (token.length() > 1) {
                    throw new IllegalArgumentException("Token error '" + token + "'");
                }

                try {
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Token error '" + token + "'");
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
            sb.append(" ");
        }
        return sb + "<- Top";
    }

    private Map<String, String> extractWords(String program) {
        Map<String, String> words = new HashMap<>();
        Iterator<String> tokens = Arrays.asList(program.split("\\s+")).iterator();
        while (tokens.hasNext()) {
            String funcName = "";
            StringBuilder funcBody = new StringBuilder();
            String token = tokens.next();
            if (token.equals(":")) {
                funcName = tokens.next();
                String separator = "";
                while (tokens.hasNext() && !(token = tokens.next()).equals(";")) {
                    funcBody.append(separator);
                    funcBody.append(token);
                    separator = " ";
                }
            }
            words.put(funcName, funcBody.toString());
        }

        return words;
    }
}
