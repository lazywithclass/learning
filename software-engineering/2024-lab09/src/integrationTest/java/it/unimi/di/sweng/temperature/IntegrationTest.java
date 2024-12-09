package it.unimi.di.sweng.temperature;


import it.unimi.di.sweng.temperature.view.MyTextView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(ApplicationExtension.class)
public class IntegrationTest {
  private MyTextView celsiusField;
  private MyTextView fahrenheitField;
  private MyTextView celsiusField2;

  private static final boolean HEADLESS = false;


  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(Stage stage) {
    Main m = new Main();
    m.start(stage);

    GridPane gp = (GridPane) stage.getScene().getRoot();
    ObservableList<Node> view = gp.getChildren();

    celsiusField = (MyTextView) view.get(0);
    fahrenheitField = (MyTextView) view.get(1);
    celsiusField2 = (MyTextView) view.get(2);
  }

  @Test
  public void twoTextFieldWithDifferentStrategies(FxRobot robot) {
    robot.doubleClickOn(celsiusField);
    robot.write("100");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
    assertThat(fahrenheitField.getValue()).isEqualTo("212.00");
    assertThat(celsiusField2.getValue()).isEqualTo("100.00");

    robot.doubleClickOn(celsiusField);
    robot.write("5.0");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
    assertThat(fahrenheitField.getValue()).isEqualTo("41.00");
    assertThat(celsiusField2.getValue()).isEqualTo("5.00");

    robot.doubleClickOn(fahrenheitField);
    robot.write("212");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
    assertThat(celsiusField.getValue()).isEqualTo("100.00");
    assertThat(celsiusField2.getValue()).isEqualTo("100.00");
  }
}
