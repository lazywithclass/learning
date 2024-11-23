package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HighCardDefenseStrategy implements Strategy {

    private final @NotNull Strategy next;

    public HighCardDefenseStrategy(Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        Card higherCard = null;

        for (Card card : cards) {
            if (!CardUtils.isFigure(card)) {
                if (higherCard == null) {
                    higherCard = card;
                } else if (higherCard.getRank().ordinal() < card.getRank().ordinal()) {
                    higherCard = card;
                }
            }
        }

        if (higherCard.getRank().ordinal() > attackCard.getRank().ordinal()) {
            return higherCard;
        }

        return next.chooseCard(cards, attackCard);
    }
}
