package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import it.unimi.di.sweng.scartino.common.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player implements Iterable<ScartinoCard> {

  private @NotNull final String name;
  private @NotNull final List<ScartinoCard> cards = new ArrayList<>();
  private @NotNull final List<ScartinoCard> personalDeck = new ArrayList<>(); //mazzetto carte vinte
  private @NotNull Strategy attackStrategyChain;
  private @NotNull Strategy answerStrategyChain;

  public Player(@NotNull String name) {
    this.name = name;
    attackStrategyChain = Strategy.NULL;
    answerStrategyChain = Strategy.NULL;
  }

  @NotNull
  public ScartinoCard chooseAttackCard() {
    ScartinoCard card = attackStrategyChain.chooseCard(cards,null);
    cards.remove(card);
    return card;
  }

  @Override
  @NotNull
  public String toString() {
    return "%s <%d> :  %s".formatted(name, getPoints(), cards);
  }

  public void collectCards(ScartinoCard attackCard, ScartinoCard answerCard) {
    personalDeck.add(answerCard);
    personalDeck.add(attackCard);
  }

  public void takeDrawnCard(@NotNull Card drawn) {
    cards.add(ScartinoCard.of(drawn));
  }

  public void setAttackStrategyChain(@NotNull Strategy attackStrategyChain) {
    this.attackStrategyChain = attackStrategyChain;
  }

  public void setAnswerStrategyChain(@NotNull Strategy answerStrategyChain) {
    this.answerStrategyChain = answerStrategyChain;
  }

  @NotNull
  public Card chooseAnswerCard(@NotNull Card attackCard) {
    //TODO implementare
    return null;
  }

  public int handSize()  {
    return cards.size();
  }

  public int getPoints() {
    return personalDeck
            .stream()
            .mapToInt(ScartinoCard::rankToValue)
            .sum();
  }

  @Override
  public @NotNull Iterator<ScartinoCard> iterator() {
    return cards.iterator();
  }
}
