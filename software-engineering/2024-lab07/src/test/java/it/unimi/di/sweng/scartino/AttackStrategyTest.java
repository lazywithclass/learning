package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AttackStrategyTest {

    @Test
    public void nullAttackStrategyTest() {
        var nullStrategy = Strategy.NULL;
        List<Card> cards = TestUtils.cardsFrom("1B,2B,3B");
        Card card = nullStrategy.chooseCard(cards, null);
        assertThat(card).isEqualTo(Card.of("1B"));
    }

    @Test
    public void higherCardAttackStrategyTest() {
        var higherCardStrategy = new HigherCardAttackStrategy();
        List<Card> cards = TestUtils.cardsFrom("1B,2B,3B");
        Card card = higherCardStrategy.chooseCard(cards, null);
        assertThat(card).isEqualTo(Card.of("3B"));
    }

    @Test
    public void lowerCardAttackStrategyTest() {
        var lowerCardStrategy = new LowerSameSuitCardAttackStrategy(Strategy.NULL);
        List<Card> cards = TestUtils.cardsFrom("5B,6B,7B");
        Card card = lowerCardStrategy.chooseCard(cards, null);
        assertThat(card).isEqualTo(Card.of("5B"));
    }

    @Test
    public void cardsOfDifferentSuitsTest() {
        Strategy next = mock();
        var lowerCardStrategy = new LowerSameSuitCardAttackStrategy(next);
        List<Card> cards = TestUtils.cardsFrom("5B,6S,7B");
        lowerCardStrategy.chooseCard(cards, null);
        verify(next, times(1)).chooseCard(cards, null);
    }

}
