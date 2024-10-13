package it.unimi.di.sweng.lab02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BowlingGame implements Bowling {

    static class Frame {
        private int first;
        private int second;
        private int bonus;
        private boolean isFirst = true;
        private boolean isExtra;

        private Frame next;

        public boolean isSpare() {
            return first + second == 10 && first < 10;
        }

        public boolean isStrike() {
            return first == 10;
        }

        public int total() {
            return first + second + bonus;
        }

        public int calculateBonus() {

        }
    }

    private int count = 0;
    private Frame current = new Frame();
    private final Frame head = current;


    @Override
    public void roll(int pins) {
        if (current.isFirst) {
            current.first = pins;
            current.isFirst = false;
            if (current.isStrike()) {
                Frame next = new Frame();
                current.next = next;
                current = next;

            }
        } else {
            current.second = pins;
            Frame next = new Frame();
            current.next = next;
            current = next;
        }

        count++;
    }

    // horrific code, I tried using Optional instead of null
    @Override
    public int score() {
        int score = 0;

        Frame current = head;
        for (int i = 1; i <= 9; i++) {
            if (current.isSpare()) {
                current.bonus = Optional
                        .ofNullable(current.next)
                        .flatMap(f -> Optional.of(f.first)).orElse(0);
            }

            if (current.isStrike()) {
                Optional<Frame> next = Optional.ofNullable(current.next);
                if (next.flatMap(f -> Optional.of(f.isStrike())).orElse(false)) {
                    current.bonus = next.get().first;
                    Optional<Frame> nextNext = Optional.ofNullable(next.get().next);
                    if (nextNext.flatMap(f -> Optional.of(f.isStrike())).orElse(false)) {
                        current.bonus += nextNext.get().first;
                    }
                } else {
                    current.bonus = next.flatMap(f -> Optional.of(f.total())).orElse(0);
                }
            }

            score += current.total();
            current = current.next;
        }

        while (current != null) {
            if (current.isStrike()) {
                Optional<Frame> next = Optional.ofNullable(current.next);
                if (next.flatMap(f -> Optional.of(f.isStrike())).orElse(false)) {
                    score += next.get().first;
                    Optional<Frame> nextNext = Optional.ofNullable(next.get().next);
                    if (nextNext.flatMap(f -> Optional.of(f.isStrike())).orElse(false)) {
                        score += nextNext.get().first;
                    }
                } else {
                    score += next.flatMap(f -> Optional.of(f.total())).orElse(0);
                }
            }
            current = current.next;
        }

        return score;
    }
}
