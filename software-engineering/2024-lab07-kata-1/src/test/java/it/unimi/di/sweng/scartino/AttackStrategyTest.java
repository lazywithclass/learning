package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AttackStrategyTest {

    @Test
    public void highestCardTest() {
        HighestCardAttackStrategy strategy = new HighestCardAttackStrategy();
        List<ScartinoCard> cards = List.of(ScartinoCard.of("7B"), ScartinoCard.of("6B"), ScartinoCard.of("5B"));
        ScartinoCard card = strategy.chooseCard(cards, null);
        assertThat(card).isEqualTo(ScartinoCard.of("7B"));
    }

    @Test
    public void highestCardWithFiguresTest() {
        HighestCardAttackStrategy strategy = new HighestCardAttackStrategy();
        List<ScartinoCard> cards = List.of(ScartinoCard.of("7B"), ScartinoCard.of("RB"), ScartinoCard.of("CB"));
        ScartinoCard card = strategy.chooseCard(cards, null);
        assertThat(card).isEqualTo(ScartinoCard.of("7B"));
    }
}
