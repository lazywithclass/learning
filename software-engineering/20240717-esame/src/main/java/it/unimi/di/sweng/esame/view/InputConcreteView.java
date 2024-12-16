package it.unimi.di.sweng.esame.view;

import it.unimi.di.sweng.esame.presenter.IPresenter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class InputConcreteView extends Region implements InputView {

    private final @NotNull TextField f1 = new TextField();
    private final @NotNull TextField f2 = new TextField();
    private final @NotNull Button button;
    private final @NotNull Label error = new Label("");


    public InputConcreteView(String field1, String field2, String buttonName) {
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2))));

        f1.setPromptText(field1);
        f2.setPromptText(field2);
        button =  new Button(buttonName);
        f1.setPrefColumnCount(10);
        f2.setPrefColumnCount(8);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(f1, 0, 0);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(f2, 1, 0);
        grid.add(button, 2, 0);
        grid.add(error, 0, 1);

        this.getChildren().add(grid);
    }

    public void addHandlers(@NotNull IPresenter presenter) {
        button.setOnAction(eh -> presenter.action(f1.getText(), f2.getText()));
    }

    @Override
    public void showError(@NotNull String s) {
        error.setText(s);
        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5.0), Insets.EMPTY)));
    }

    @Override
    public void showSuccess() {
        error.setText("");
        f1.clear();
        f2.clear();
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));
    }
}
