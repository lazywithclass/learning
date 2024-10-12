package it.unimi.di.sweng.lab02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BowlingGame implements Bowling {

    private final static class Frame {
        private Integer first;
        private Integer second;
        private Integer bonus = 0;
        private boolean extra;


        public boolean isFirst() {
            return Optional.ofNullable(first).isEmpty();
        }

        public boolean isSpare() {
            int f = Optional.ofNullable(first).orElse(0);
            int s = Optional.ofNullable(second).orElse(0);
            return f >= 0 && f < 10 && s > 0 && s <= 10 && f + s == 10;
        }

        public boolean isStrike() {
            int f = Optional.ofNullable(first).orElse(0);
            return f == 10;
        }

        public int getFirst() {
            return Optional.ofNullable(first).orElse(0);
        }

        public int score() {
            return Optional.ofNullable(first).orElse(0) +
                    Optional.ofNullable(second).orElse(0) +
                    Optional.ofNullable(bonus).orElse(0);
        }
    }

    private List<Frame> frames = new ArrayList<>();
    private Frame current = new Frame();

    @Override
    public void roll(int pins) {
        if (frames.size() > 9) {
            current.extra = true;
        }

        if (current.isFirst()) {
            current.first = pins;
            if (current.isStrike()) {
                frames.add(current);
                current = new Frame();
            }
        } else {
            current.second = pins;
            frames.add(current);
            current = new Frame();
        }
    }

    @Override
    public int score() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            Frame frame = frames.get(i);

            if (frame.isSpare()) {
                if (i + 1 < frames.size()) {
                    frame.bonus = frames.get(i + 1).getFirst();
                }
            }

            if (frame.isStrike()) {
                if (i + 1 < frames.size()) {
                    if (frames.get(i + 1).isStrike()) {
                        frame.bonus += frames.get(i + 1).score();
                        if (i + 2 < frames.size()) {
                            frame.bonus += frames.get(i + 2).getFirst();
                        }
                    } else {
                        frame.bonus += frames.get(i + 1).score();
                    }
                }
            }

            if (!frame.extra) {
                score += frame.score();
            }
        }

        return score;
    }
}
