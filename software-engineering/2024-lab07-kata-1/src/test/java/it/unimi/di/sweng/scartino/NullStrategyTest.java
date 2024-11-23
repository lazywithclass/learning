package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NullStrategyTest {

    @Test
    public void returnsTheFirstCardTest() {
        List<ScartinoCard> cards = List.of(ScartinoCard.of("1B"), ScartinoCard.of("2B"), ScartinoCard.of("3B"));
        ScartinoCard card = Strategy.NULL.chooseCard(cards, null);
        assertThat(card).isEqualTo(ScartinoCard.of("1B"));
    }


}
