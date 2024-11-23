package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DefenseStrategyTest {

    @Test
    public void invalidateFigureDefenseStrategyTest() {
        List<Card> cards = TestUtils.cardsFrom("1B,2B,RB");
        Strategy strategy = new InvalidateFigureDefenseStrategy(Strategy.NULL);
        assertThat(strategy.chooseCard(cards, Card.of("5D"))).isEqualTo(Card.of("RB"));
    }

    @Test
    public void ifDoesntHaveFigureThenGoesNextTest() {
        List<Card> cards = TestUtils.cardsFrom("1B,2B,3B");
        Strategy next = mock();
        Strategy strategy = new InvalidateFigureDefenseStrategy(next);
        strategy.chooseCard(cards, Card.of("5D"));
        verify(next, times(1)).chooseCard(cards, Card.of("5D"));
    }

    @Test
    public void chooseHighCardTest() {
        List<Card> cards = TestUtils.cardsFrom("1B,2B,7B");
        Strategy strategy = new HighCardDefenseStrategy(Strategy.NULL);
        assertThat(strategy.chooseCard(cards, Card.of("6B"))).isEqualTo(Card.of("7B"));
    }

    @Test
    public void ifDoesntHaveAHigherCardThanAttackerGoesNext() {
        List<Card> cards = TestUtils.cardsFrom("1B,2B,3B");
        Strategy next = mock();
        Strategy strategy = new HighCardDefenseStrategy(next);
        strategy.chooseCard(cards, Card.of("5B"));
        verify(next, times(1)).chooseCard(cards, Card.of("5B"));
    }

}
