package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.stream.StreamSupport;

public class HighestCardAttackStrategy implements Strategy {

    @Override
    public @NotNull ScartinoCard chooseCard(@NotNull Iterable<ScartinoCard> cards, @Nullable ScartinoCard attackCard) {
        // select the highest card
        return StreamSupport.stream(cards.spliterator(), false)
                .max(Comparator.comparingInt(ScartinoCard::rankToValue))
                .get();
    }
}
