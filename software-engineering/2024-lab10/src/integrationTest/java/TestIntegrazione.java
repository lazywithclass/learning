import it.unimi.di.sweng.slalom.Main;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import it.unimi.di.sweng.slalom.views.DisplayView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.assertj.core.util.introspection.FieldSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {
  private static final boolean HEADLESS = false;

  private DisplayView firstRun;
  private DisplayView secondRun;
  private DisplayView totalRun;
  private NextSkierView nextSkier;
  private Label errorMessage;

  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(Stage primaryStage) {
    Main m = new Main();
    m.start(primaryStage);

    GridPane gp = (GridPane) primaryStage.getScene().getRoot();
    ObservableList<Node> view = gp.getChildren();

    nextSkier = (NextSkierView) view.get(0);

    firstRun = (DisplayView) view.get(1);
    secondRun = (DisplayView) view.get(2);
    totalRun = (DisplayView) view.get(3);
    errorMessage = FieldSupport.EXTRACTION.fieldValue("error", Label.class, nextSkier);

  }

  @Test
  public void testSomething(FxRobot robot) {
    assertThat(firstRun.get(2)).startsWith("BRIGNONE Federica").endsWith("57.98");
    assertThat(nextSkier.getName()).startsWith("STJERNESUND Thea Louise");

    robot.doubleClickOn(nextSkier);
    robot.write("57.50");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
    assertThat(secondRun.get(0)).startsWith("STJERNESUND").endsWith("57.50");
    assertThat(totalRun.get(0)).startsWith("STJERNESUND").endsWith("1:56.89");

    robot.doubleClickOn(nextSkier);
    robot.write("58.90");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);

    robot.doubleClickOn(nextSkier);
    robot.write("58.81");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);

    assertThat(secondRun.get(0)).startsWith("STJERNESUND").endsWith("57.50");
    assertThat(secondRun.get(1)).startsWith("VLHOVA");
    assertThat(secondRun.get(2)).startsWith("LIENSBERGER");

    assertThat(totalRun.get(0)).startsWith("STJERNESUND");
    assertThat(totalRun.get(1)).startsWith("VLHOVA").endsWith("1:58.15");
    assertThat(totalRun.get(2)).startsWith("LIENSBERGER").endsWith("1:58.24");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      -20, Tempo deve essere positivo
      76.5, Il tempo della singola manche deve essere inferiore ai 60 secondi
      pippo, Il tempo deve essere un numero 
      """)
  public void testInputNonValido(String input, String expected, FxRobot robot) {
    robot.doubleClickOn(nextSkier);
    robot.write(input);
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
    verifyThat(errorMessage, hasText(expected));
  }


  @Test
  public void testInputNonValido2(FxRobot robot) {
    for (int i = 0; i < 15; i++) {
      robot.doubleClickOn(nextSkier);
      robot.write("55");
      robot.press(KeyCode.ENTER);
      robot.release(KeyCode.ENTER);
      verifyThat(errorMessage, hasText(""));
    }
    robot.doubleClickOn(nextSkier);
    robot.write("55");
    robot.press(KeyCode.ENTER);
    robot.release(KeyCode.ENTER);
    verifyThat(errorMessage, hasText("Gara già finita"));
  }

}
