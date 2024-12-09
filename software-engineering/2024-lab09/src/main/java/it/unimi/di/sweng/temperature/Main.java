package it.unimi.di.sweng.temperature;

import it.unimi.di.sweng.temperature.model.TemperatureModel;
import it.unimi.di.sweng.temperature.presenter.FahrenheitStrategy;
import it.unimi.di.sweng.temperature.presenter.NoActionTemperaturePresenter;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import it.unimi.di.sweng.temperature.presenter.TemperaturePresenter;
import it.unimi.di.sweng.temperature.view.MyTextView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Temperature 2024");

    MyTextView celsiusField = new MyTextView("Celsius");
    MyTextView celsiusField2 = new MyTextView("Celsius not editable");
    MyTextView fahrenheitField = new MyTextView("Fahrenheit");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(celsiusField, 1, 0);
    gridPane.add(fahrenheitField, 1, 1);
    gridPane.add(celsiusField2, 1, 2);

    TemperatureModel model = new TemperatureModel();

    TemperaturePresenter presenter = new TemperaturePresenter(celsiusField, model, ScaleStrategy.IDENTITY);
    TemperaturePresenter presenterFahrenheit = new TemperaturePresenter(fahrenheitField, model, FahrenheitStrategy.INSTANCE);
    NoActionTemperaturePresenter presenter2 = new NoActionTemperaturePresenter(celsiusField2, model, ScaleStrategy.IDENTITY);
    model.addObserver(presenter);
    model.addObserver(presenterFahrenheit);
    model.addObserver(presenter2);

    celsiusField.addHandlers(presenter);
    fahrenheitField.addHandlers(presenterFahrenheit);
    celsiusField2.addHandlers(presenter2);

    model.notifyObservers();

    Scene scene = new Scene(gridPane);
    stage.setScene(scene);
    stage.show();
  }
}
