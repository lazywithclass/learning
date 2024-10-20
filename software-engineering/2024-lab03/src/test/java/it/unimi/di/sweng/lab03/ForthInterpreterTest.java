package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


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
  @CsvSource({"1, 1 <- Top", "1 2, 1 2 <- Top"})
  public void numericInput(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2, 1 2 <- Top",
          "'1\n2', 1 2 <- Top",
          "'1   2 \n3', 1 2 3 <- Top"
  })
  public void notOnlySpacesInput(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 +, 3 <- Top",
          "1 2 + 5 +, 8 <- Top"
  })
  public void sum(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }

  @ParameterizedTest
  @ValueSource(strings = {
          "1 2+",
          "1 2 +5 +"
  })
  public void incorrectInputNoSpace(String program) {
    assertThatThrownBy(() -> interpreter.input(program)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void stackUnderflow() {
    assertThatThrownBy(() -> interpreter.input("1 +")).isInstanceOf(IllegalArgumentException.class).hasMessageContainingAll("Stack Underflow");
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 *, 2 <- Top",
          "1 2 /, 0 <- Top"
  })
  public void mulDiv(String input, String expected) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(expected);
  }

}
