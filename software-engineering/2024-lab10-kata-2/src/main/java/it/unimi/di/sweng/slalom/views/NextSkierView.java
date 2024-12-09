package it.unimi.di.sweng.slalom.views;


import it.unimi.di.sweng.slalom.presenters.Presenter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class NextSkierView extends Region implements InputView, OutputView {
  @NotNull
  private final TextField textField;
  @NotNull
  private final Label error;
  @NotNull
  private final Label name;

  public NextSkierView() {
    error = new Label("");
    name = new Label("NEXT SKIER");
    textField = new TextField();
    name.setFont(Font.font("sans", 20));
    name.setMinWidth(300);
    textField.setMaxWidth(100);
    textField.setFont(Font.font("sans", 20));
    setBackground(new Background(
        new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
    setBorder(new Border(
        new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

    name.setPadding(new Insets(10, 10, 10, 10));
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));

    grid.add(name, 0, 0);
    grid.add(textField, 1, 0);
    grid.add(error, 0, 1, 2, 1);

    this.getChildren().add(grid);
  }

  public void addHandlers(@NotNull Presenter presenter) {
    textField.setOnAction(eh -> presenter.action(name.getText(), textField.getText()));   // tasto invio nella casella di testo
  }

  public @NotNull String getName() {
    return name.getText();
  }

  @Override
  public void showError(@NotNull String s) {
    error.setText(s);
    setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
  }

  @Override
  public void showSuccess() {
    error.setText("");
    textField.clear();
    setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));
  }

  @Override
  public void set(int i, @NotNull String s) {
    name.setText(s);
    textField.setText("");
  }

  @Override
  public int size() {
    return 1;
  }
}
