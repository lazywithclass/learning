package it.unimi.di.sweng.lab02;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame implements Bowling {

    private static class Frame {
        protected List<Integer> pins = new ArrayList<>();
        protected int bonus;
        private Frame next;

        private int total() {
            return pins.stream().reduce(0, Integer::sum);
        }

        public int score() {
            return total() + bonus;
        }

        public boolean isSpare() {
            return total() == 10 && firstThrow() < 10;
        }

        public boolean isStrike() {
            if (pins.isEmpty()) {
                return false;
            }
            return firstThrow() == 10;
        }

        public void addPins(int pin) {
            pins.add(pin);
        }

        public int firstThrow() {
            if (pins.isEmpty()) {
                return 0; // not my best moment
            }
            return pins.get(0);
        }

        public boolean isFirst() {
            return pins.size() == 1;
        }

        public boolean hasNext() {
            return next != null;
        }
    }

    private Frame current = new Frame();
    private Frame head = current;
    private Frame last = new Frame();
    private int rollsCount = 1;

    @Override
    public void roll(int pins) {
        if (rollsCount == 21) {
            last.addPins(pins);
            return;
        }

        current.addPins(pins);
        if (current.isFirst()) {
            if (current.isStrike()) {
                rollsCount++;
                Frame nu = new Frame();
                current.next = nu;
                current = nu;
            }
        } else {
            Frame nu = new Frame();
            current.next = nu;
            current = nu;
        }

        rollsCount++;
    }

    @Override
    public int score() {
        int score = 0;
        Frame current = head;
        while (current.hasNext()) {
            if (current.isSpare() && current.hasNext()) {
                current.bonus = current.next.firstThrow();
            }
            if (current.isStrike() && current.hasNext()) {
                current.bonus = current.next.score();
            }
            score += current.score();
            current = current.next;
        }
        return score + last.score();
    }
}
