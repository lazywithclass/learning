package it.unimi.di.sweng.esame.view;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.introspection.FieldSupport.EXTRACTION;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

import it.unimi.di.sweng.esame.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.assertj.core.util.introspection.FieldSupport;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javax.imageio.ImageIO;
import java.io.File;


@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {
  private static final boolean HEADLESS = false;

  private Stage stage;
  private OutputView excursionsDisplay;
  private OutputView debitsDisplay;
  private Label errorMessage;
  private TextField name;
  private TextField excursion;
  private Button payButton;

  private Label errorMessage1;
  private TextField name1;
  private TextField amount;
  private Button bookButton;

  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(@NotNull Stage primaryStage) {

    Main m = new Main();
    m.start(primaryStage);

    stage = primaryStage;
    GridPane gp = (GridPane) primaryStage.getScene().getRoot();
    ObservableList<Node> view = gp.getChildren();


    excursionsDisplay = (DisplayView) view.get(0);
    InputConcreteView inputBooking = (InputConcreteView) view.get(1);
    debitsDisplay = (DisplayView) view.get(2);
    InputConcreteView inputPayment = (InputConcreteView) view.get(3);

    errorMessage = FieldSupport.EXTRACTION.fieldValue("error", Label.class, inputBooking);
    name = FieldSupport.EXTRACTION.fieldValue("f1", TextField.class, inputBooking);
    excursion = FieldSupport.EXTRACTION.fieldValue("f2", TextField.class, inputBooking);
    bookButton = FieldSupport.EXTRACTION.fieldValue("button", Button.class, inputBooking);

    errorMessage1 = FieldSupport.EXTRACTION.fieldValue("error", Label.class, inputPayment);
    name1 = FieldSupport.EXTRACTION.fieldValue("f1", TextField.class, inputPayment);
    amount = FieldSupport.EXTRACTION.fieldValue("f2", TextField.class, inputPayment);
    payButton = FieldSupport.EXTRACTION.fieldValue("button", Button.class, inputPayment);
  }


  // TESTS

  @Test
  void testDisplayAvailableExcursions() {
    assertThat(excursionsDisplay.get(4)).isEqualTo("Brera 23.01 5");
    assertThat(excursionsDisplay.get(3)).isEqualTo("Duomo Terrace 30.00 10");
    assertThat(excursionsDisplay.get(2)).isEqualTo("Scala 30.00 20");
    assertThat(excursionsDisplay.get(1)).isEqualTo("Tram Tour 45.33 25");
    assertThat(excursionsDisplay.get(0)).isEqualTo("Walking Tour 20.50 30");
  }

  @ParameterizedTest()
  @CsvSource(textBlock = """
      '' , Brera, Empty customer name
      """)
  void testInputBookingError(String nameString, String excursionString, String errorString, @NotNull FxRobot robot) {
    book(nameString, excursionString, robot);

    verifyThat(errorMessage, hasText(errorString));
  }

  @Test
  public void testDisplayStartOK() {
    assertThat(excursionsDisplay.get(0)).startsWith("Brera").endsWith("5");
    assertThat(debitsDisplay.get(0)).isEqualTo("");
  }

  @Test
  void testCorrectBookingInputSequence(@NotNull FxRobot robot) {
    book("Carlo", "Brera", robot);
    book("Mattia", "Brera", robot);
    book("Carlo", "Walking Tour", robot);

    verifyThat(errorMessage, hasText(""));
    assertThat(excursionsDisplay.get(0)).startsWith("Brera").endsWith("3");
    assertThat(debitsDisplay.get(0)).startsWith("Carlo").endsWith("43.51");
    assertThat(debitsDisplay.get(1)).startsWith("Mattia").endsWith("23.01");
  }




  // UTILITY FUNCTIONS

  private void book(String nameString, String excursionString, @NotNull FxRobot robot) {
    writeOnGui(robot, name, nameString);
    writeOnGui(robot, excursion, excursionString);
    robot.clickOn(bookButton);
  }

  private void pay(String nameString, String amountString, @NotNull FxRobot robot) {
    writeOnGui(robot, name1, nameString);
    writeOnGui(robot, amount, amountString);
    robot.clickOn(payButton);
  }

  private void writeOnGui(@NotNull FxRobot robot, TextField input, String text) {
    robot.doubleClickOn(input);
    robot.write(text, 0);
  }

}
