package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;

public class CardUtils {

    // L'idea iniziale era di fare un Pattern Adapter avendo una ScartinoCard
    // il problema e' che sarebbe stata una modifica troppo pervasiva allontanandoci
    // per troppo tempo dal RED GREEN cycle

    public static boolean isFigure(Card card) {
        return card.getRank().ordinal() >= Rank.FANTE.ordinal();
    }
}
