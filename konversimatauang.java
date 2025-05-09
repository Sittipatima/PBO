package com.example.appjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class konversimatauang extends Application{
        private static final double KURS_USD_TO_IDR = 15000.0; // Kurs tetap

        @Override
        public void start(Stage primaryStage) {
            Label inputLabel = new Label("Masukkan Nilai:");
            TextField inputField = new TextField();

            ComboBox<String> directionBox = new ComboBox<>();
            directionBox.getItems().addAll("USD ke IDR", "IDR ke USD");

            Button convertButton = new Button("Konversi");
            convertButton.setStyle("-fx-background-color: #009688; -fx-text-fill: white;"); // 💡 Tambahkan setelah buat button
            Label resultLabel = new Label("Hasil: ");

            convertButton.setOnAction(e -> {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    String direction = directionBox.getValue();
                    if (direction == null) {
                        resultLabel.setText("Pilih arah konversi!");
                        return;
                    }

                    double result;
                    if (direction.equals("USD ke IDR")) {
                        result = input * KURS_USD_TO_IDR;
                        resultLabel.setText(String.format("Hasil: Rp %, .2f", result));
                    } else {
                        result = input / KURS_USD_TO_IDR;
                        resultLabel.setText(String.format("Hasil: $%.4f", result));
                    }

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Input tidak valid.");
                }
            });

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(20));
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setAlignment(Pos.CENTER);
            grid.setStyle("-fx-background-color: #e0f7fa;");
            grid.add(inputLabel, 0, 0);
            grid.add(inputField, 1, 0);
            grid.add(directionBox, 1, 1);
            grid.add(convertButton, 0, 2);
            grid.add(resultLabel, 1, 2);

            Scene scene = new Scene(grid, 400, 200);
            primaryStage.setTitle("Konversi USD ↔ IDR");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


