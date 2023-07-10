package com.ReservationSystemUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class ReservationSystemUI extends Application {
    private ReservationSystem reservationSystem = new ReservationSystem();
    private TextArea reservationsTextArea;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reservation System");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label dateLabel = new Label("Date:");
        TextField dateTextField = new TextField();
        Label guestsLabel = new Label("Number of Guests:");
        TextField guestsTextField = new TextField();

        Button makeReservationButton = new Button("Make Reservation");
        Button viewReservationsButton = new Button("View Reservations");
        Button cancelReservationButton = new Button("Cancel Reservation");

        reservationsTextArea = new TextArea();
        reservationsTextArea.setEditable(false);

        makeReservationButton.setOnAction(e -> {
            String name = nameTextField.getText();
            String date = dateTextField.getText();
            int numberOfGuests = Integer.parseInt(guestsTextField.getText());
            Reservation reservation = reservationSystem.makeReservation(name, date, numberOfGuests);
            reservationsTextArea.appendText("Reservation made with ID " + reservation.getId() + "\n");
            clearFields();
        });

        viewReservationsButton.setOnAction(e -> {
            reservationsTextArea.clear();
            List<Reservation> reservations = reservationSystem.getReservations();
            for (Reservation r : reservations) {
                reservationsTextArea.appendText(r.getId() + " - " + r.getName() + " - " + r.getDate() + " - " + r.getNumberOfGuests() + "\n");
            }
        });

        cancelReservationButton.setOnAction(e -> {
            int id = Integer.parseInt(ReservationIdTextField.getText());
            if (reservationSystem.cancelReservation(id)) {
                reservationsTextArea.appendText("Reservation canceled\n");
            } else {
                reservationsTextArea.appendText("Reservation not found\n");
            }
            clearFields();
        });

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(dateLabel, 0, 1);
        gridPane.add(dateTextField, 1, 1);
        gridPane.add(guestsLabel, 0, 2);
        gridPane.add(guestsTextField, 1, 2);
        gridPane.add(makeReservationButton, 0, 3);
        gridPane.add(viewReservationsButton, 1, 3);
        gridPane.add(cancelReservationButton, 0, 4);
        gridPane.add(reservationsTextArea, 0, 5, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields() {
        nameTextField.clear();
        dateTextField.clear();
        guestsTextField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
