package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvalidateFigureDefenseStrategy implements Strategy {

    private final @NotNull Strategy next;

    public InvalidateFigureDefenseStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Iterable<Card> cards, @Nullable Card attackCard) {
        for (Card card : cards) {
            if (CardUtils.isFigure(card) && attackCard.getRank() == Rank.CINQUE) {
                return card;
            }
        }
        return next.chooseCard(cards, attackCard);
    }

}
