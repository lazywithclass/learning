package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;

public class ScartinoCard {

    private Card card;

    private ScartinoCard() {
    }

    public static ScartinoCard of(Card card) {
        ScartinoCard scartinoCard = new ScartinoCard();
        scartinoCard.card = card;
        return scartinoCard;
    }

    public static ScartinoCard of(String card) {
        ScartinoCard scartinoCard = new ScartinoCard();
        scartinoCard.card = Card.of(card);
        return scartinoCard;
    }

    public int rankToValue() {
        if (card.getRank().ordinal() > 6) {
            return 0;
        }

        return card.getRank().ordinal() + 1;
    }

    public Rank getRank() {
        return card.getRank();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        ScartinoCard other = (ScartinoCard) obj;
        return this.card.equals(other.card);
    }
}
