package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Player implements Iterable<Card> {

  private @NotNull final String name;
  private @NotNull final List<Card> cards = new ArrayList<>();
  private @NotNull final List<Card> personalDeck = new ArrayList<>(); //mazzetto carte vinte
  private @NotNull Strategy attackStrategyChain;
  private @NotNull Strategy answerStrategyChain;

  public Player(@NotNull String name) {
    this.name = name;
    attackStrategyChain = Strategy.NULL;
    answerStrategyChain = Strategy.NULL;
  }

  @NotNull
  public Card chooseAttackCard() {
    Card card = attackStrategyChain.chooseCard(cards,null);
    cards.remove(card);
    return card;
  }


  @Override
  @NotNull
  public String toString() {
    return "%s <%d> :  %s".formatted(name, getPoints(), cards);
  }

  public void collectCards(Card attackCard, Card answerCard) {
    personalDeck.add(answerCard);
    personalDeck.add(attackCard);
  }

  public void takeDrawnCard(@NotNull Card drawn) {
    cards.add(drawn);
  }

  public void setAttackStrategyChain(@NotNull Strategy attackStrategyChain) {
    this.attackStrategyChain = attackStrategyChain;
  }

  public void setAnswerStrategyChain(@NotNull Strategy answerStrategyChain) {
    this.answerStrategyChain = answerStrategyChain;
  }

  @NotNull
  public Card chooseAnswerCard(@NotNull Card attackCard) {
    return answerStrategyChain.chooseCard(cards, attackCard);
  }

  public int handSize()  {
    return cards.size();
  }

  public int getPoints() {
    int points = 0;

    for (Card card : cards) {
      if (!CardUtils.isFigure(card)) {
        points += card.getRank().ordinal() + 1;
      }
    }

    return points;
  }

  @Override
  public @NotNull Iterator<Card> iterator() {
    return cards.iterator();
  }
}
