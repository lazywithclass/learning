package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Player");
    }

    @Test
    public void playerIsIterableTest() {
        Card card = Card.of("1B");
        player.takeDrawnCard(card);
        assertThat(player.iterator().next()).isEqualTo(ScartinoCard.of("1B"));
    }

    @Test
    public void hasZeroCardsAtInitTest() {
        assertThat(player.handSize()).isEqualTo(0);
    }

    @Test
    public void hasOneCardAfterDrawnTest() {
        player.takeDrawnCard(any());
        assertThat(player.handSize()).isEqualTo(1);
    }

    @Test
    public void zeroPointsAtInitTest() {
        assertThat(player.getPoints()).isEqualTo(0);
    }

    @Test
    public void pointsAreTheSumOfCardsValuesTest() {
        player.collectCards(ScartinoCard.of("1B"), ScartinoCard.of("2B"));
        assertThat(player.getPoints()).isEqualTo(3);
    }

    @Test
    public void pointsAreTheSumOfCardsValuesTakingFiguresIntoAccountTest() {
        player.collectCards(ScartinoCard.of("1B"), ScartinoCard.of("FB"));
        player.collectCards(ScartinoCard.of("CB"), ScartinoCard.of("RB"));
        assertThat(player.getPoints()).isEqualTo(1);
    }
}
