package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HigherCardAttackStrategy implements Strategy {
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
        
        return higherCard;
    }
}
