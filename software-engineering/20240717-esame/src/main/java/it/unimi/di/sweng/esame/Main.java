package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Booking;
import it.unimi.di.sweng.esame.model.Excursion;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.ModelState;
import it.unimi.di.sweng.esame.presenter.InputPresenter;
import it.unimi.di.sweng.esame.presenter.OutputPresenter;
import it.unimi.di.sweng.esame.presenter.PayDebitPresenter;
import it.unimi.di.sweng.esame.view.DisplayView;
import it.unimi.di.sweng.esame.view.InputConcreteView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main extends Application {
    public static final int VIEWSIZE = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) {

        primaryStage.setTitle("Excursion Booking");

        DisplayView debits = new DisplayView(VIEWSIZE, "Bookers' Debits");
        DisplayView excursions = new DisplayView(VIEWSIZE, "Available Excursions");

        InputConcreteView inputBooking = new InputConcreteView("Name", "Excursion", "BOOK");
        InputConcreteView inputPayment = new InputConcreteView("Name", "Amount", "PAY");

        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.add(excursions, 0, 0);
        gridPane.add(inputBooking, 0, 1);

        gridPane.add(debits, 1, 0);
        gridPane.add(inputPayment, 1, 1);


        //TODO: modificare e completare il seguente codice per istanziare e collegare i vari componenti
        Model model = new Model();
        Scanner scanner = new Scanner(ModelState.class.getResourceAsStream("/excursions.csv"));

        OutputPresenter bookingPresenter = new OutputPresenter(excursions, () -> {
            List<Excursion> excursionList = new ArrayList<>(model.getState());
            excursionList.sort(Excursion::compareTo);
            return excursionList.stream().map(Excursion::toString).toList();
        });
        model.addObserver(bookingPresenter);


        OutputPresenter debitPresenter = new OutputPresenter(debits, () -> model.getBookings().entrySet().stream().map(entry -> String.format("%s %.2f", entry.getKey(), entry.getValue())).toList());
        model.addObserver(debitPresenter);

        InputPresenter presenter = new InputPresenter(model, inputBooking);

        inputBooking.addHandlers(presenter);

        PayDebitPresenter payDebitPresenter = new PayDebitPresenter(model, inputPayment);
        inputPayment.addHandlers(payDebitPresenter);

        model.readFile(scanner);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
