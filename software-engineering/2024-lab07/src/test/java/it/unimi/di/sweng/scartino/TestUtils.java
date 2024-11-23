package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doAnswer;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
  @SafeVarargs
  public static <T> void whenIterated(Iterable<T> p, T... d) {
    doAnswer(a -> List.of(d).iterator()).when(p).iterator();
  }


  // in Card abbiamo messo il costruttore statico of(String) che
  // viene chiamato implicitamente dai test parametrici
  // vedi esempio qui sotto

  @ParameterizedTest
  @CsvSource({
      "CS,AB,3D",
      "RC,RS,7D"
  })
  public void check(Card a, Card b, Card c) {
    System.out.println(a + ", " + b + ", " + c);
  }


  //Con il metodo cardsFrom potete costruire una lista di Card a partire da una stringa

  public static List<Card> cardsFrom(String str) {
    return Arrays.stream(str.split("\\s*,\\s*"))
        .map(Card::of).toList();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      '5B,7B,2S,8B', 5B
      '2B,7B,2S,8B', 2S
      '2B,7B,2S,8B', 7B
      """)
  void failMinCardToGetPoints(String cardsString, Card card) {
    List<Card> cards = TestUtils.cardsFrom(cardsString);
    assertThat(cards).contains(card);
  }
}