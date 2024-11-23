package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Suit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LowerSameSuitCardAttackStrategy implements Strategy {

    private @NotNull final Strategy next;

    public LowerSameSuitCardAttackStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        Card lowerCard = null;
        for (Card card : cards) {
            if (lowerCard == null) {
                lowerCard = card;
            } else {
                if (lowerCard.getSuit() != card.getSuit()) {
                    break;
                }

                if (lowerCard.getRank().ordinal() > card.getRank().ordinal()) {
                    lowerCard = card;
                }
            }
        }

        return next.chooseCard(cards, attackCard);
    }
}
