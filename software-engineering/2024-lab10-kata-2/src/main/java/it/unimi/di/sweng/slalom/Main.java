package it.unimi.di.sweng.slalom;


import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.presenters.FirstMancheModelReductionStrategy;
import it.unimi.di.sweng.slalom.presenters.NextSkierModelReductionStrategy;
import it.unimi.di.sweng.slalom.presenters.SecondMancheModelReductionStrategy;
import it.unimi.di.sweng.slalom.presenters.SkierPresenter;
import it.unimi.di.sweng.slalom.views.InputView;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import it.unimi.di.sweng.slalom.views.DisplayView;
import it.unimi.di.sweng.slalom.views.OutputView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
  final public static int SKIER_NUM = 15;
  final public static int SKIER_NUM_TOTAL_RANK = 5;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("2022 - Women's Giant Slalom");

    NextSkierView nextSkier = new NextSkierView();

    DisplayView firstRun = new DisplayView("First manche", SKIER_NUM);
    DisplayView secondRun = new DisplayView("Second manche", SKIER_NUM);
    DisplayView totalRun = new DisplayView("Final ranking (first " + SKIER_NUM_TOTAL_RANK + " skiers)", SKIER_NUM_TOTAL_RANK);


    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(nextSkier, 0, 0);
    GridPane.setColumnSpan(nextSkier, GridPane.REMAINING);
    gridPane.add(firstRun, 0, 1);
    gridPane.add(secondRun, 1, 1);

    gridPane.add(totalRun, 0, 2);
    GridPane.setColumnSpan(totalRun, GridPane.REMAINING);

    Model model = new Model();
    InputStream is = Main.class.getResourceAsStream("/first");
    assert is != null;
    Scanner s = new Scanner(is);
    model.readFilePrimaManche(s);

    // ESEMPIO SOLO PER LA RELAZIONE PRESENTER - MODEL

    SkierPresenter<List<Skier>, OutputView> firstManchePresenter =
            new SkierPresenter<>(new FirstMancheModelReductionStrategy(), firstRun);
    SkierPresenter<List<Skier>, OutputView> secondManchePresenter =
            new SkierPresenter<>(new SecondMancheModelReductionStrategy(), secondRun);
    SkierPresenter<Skier, NextSkierView> nextSkierPresenter =
            new SkierPresenter<>(new NextSkierModelReductionStrategy(), nextSkier);

    // ESEMPIO SOLO PER LA RELAZIONE PRESENTER - MODEL


    // TODO creare presenters e fare i collegamenti


    // TODO scommentare la riga seguente quando definito il metodo
    // model.notifyObservers()
    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);
    primaryStage.show();


  }
}
