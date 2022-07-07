package ru.qwonix.cutesuite.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RentalCalculatorApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RentalCalculatorApp.class.getResource("rental-calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(450);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Rental Calculator");
        stage.setScene(scene);
        stage.show();
    }
}