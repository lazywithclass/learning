package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
        '7B', '6B', 1
        '6B', '7B', -1
        '7B', '7B', 0
        '1C', '7B', 1
        '1B', '7S', 1
        '1S', '7D', 1
    """)
    public void establishTakerTest(Card attack, Card answer, int expected) {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Game game = new Game(p1, p2);
        int result = game.beats(attack, answer);
        assertThat(result).isEqualTo(expected);
    }
}