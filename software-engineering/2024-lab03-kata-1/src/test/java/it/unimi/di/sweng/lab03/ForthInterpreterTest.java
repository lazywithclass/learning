package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Timeout(2)
public class ForthInterpreterTest {
  private Interpreter interpreter;

  @BeforeEach
  public void setUp() {
    interpreter = new ForthInterpreter();
  }


  @Test
  public void emptyInput() {
    interpreter.input("");
    assertThat(interpreter.toString()).isEqualTo("<- Top");
  }

  @ParameterizedTest
  @CsvSource({
          "1 , 1 <- Top",
          "1 2 3 4, 1 2 3 4 <- Top"
  })
  public void simpleInput(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }


  @ParameterizedTest
  @CsvSource({
          "'1\n2' , 1 2 <- Top",
          "'1   2 \n3', 1 2 3 <- Top"
  })
  public void whitespaceInput(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2 +' , 3 <- Top",
          "'1 2 + 5 +', 8 <- Top",
          "'1 2 *' , 2 <- Top",
          "'1 2 * 5 *' , 10 <- Top",
          "'1 2 -' , -1 <- Top",
          "'1 2 /' , 0 <- Top",
          "'1 2 3 dup' , 1 2 3 3 <- Top",
          "'1 2 3 swap' , 1 3 2 <- Top",
          "'1 2 3 drop' , 1 2 <- Top",
          "'1 2 + 3 * 4 dup 5 + drop swap' , 4 9 <- Top",
          "': double 2 * ; 5 double dup double' , 10 20 <- Top"
  })
  public void operations(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2+' , Token error '2+'",
          "'1 2 +5 +', Token error '+5'",
          "1 2 + 3 * drop swap, 'Stack Underflow'",
          "1 +, 'Stack Underflow'",
          "pippo , Token error 'pippo'",
          "1 2 pippo, Token error 'pippo'",
          "1 : raddoppia 2 * ; raddoppi raddoppia , Token error 'raddoppi'"
  })
  public void wrongTokenInput(String input, String expected) {
    assertThatThrownBy(() -> interpreter.input(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContainingAll(expected);
  }
}
