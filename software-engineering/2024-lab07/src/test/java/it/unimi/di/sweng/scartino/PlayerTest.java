package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Player");
    }

    @Test
    public void playerIterableTest() {
        player.takeDrawnCard(Card.of("2B"));
        assertThat(player.iterator().next()).isEqualTo(Card.of("2B"));
    }

    @Test
    public void handSizeTest() {
        player.takeDrawnCard(Card.of("2B"));
        player.takeDrawnCard(Card.of("3B"));

        assertThat(player.handSize()).isEqualTo(2);
    }

    @Test
    public void pointsTest() {
        player.takeDrawnCard(Card.of("1B"));
        player.takeDrawnCard(Card.of("2B"));
        player.takeDrawnCard(Card.of("3B"));
        player.takeDrawnCard(Card.of("4B"));
        player.takeDrawnCard(Card.of("5B"));
        player.takeDrawnCard(Card.of("6B"));
        player.takeDrawnCard(Card.of("7B"));

        assertThat(player.getPoints()).isEqualTo(28);
    }

    @Test
    public void whenHandHasOnlyFiguresPointsTest() {
        player.takeDrawnCard(Card.of("FB"));
        player.takeDrawnCard(Card.of("CB"));
        player.takeDrawnCard(Card.of("RB"));

        assertThat(player.getPoints()).isEqualTo(0);
    }

    @Test
    public void playerStrategyUsageTest() {
        player.takeDrawnCard(Card.of("FB"));
        player.setAttackStrategyChain(Strategy.NULL);
        Card answerCard = player.chooseAnswerCard(Card.of("3D"));
        assertThat(answerCard).isEqualTo(Card.of("FB"));
    }

}
