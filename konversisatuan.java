package com.example.appjavafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
    public  class satuanpanjang extends Application{

        // Faktor konversi ke meter
        private static final Map<String, Double> konversiKeMeter = new HashMap<>();
        static {
            konversiKeMeter.put("km", 1000.0);
            konversiKeMeter.put("hm", 100.0);
            konversiKeMeter.put("dam", 10.0);
            konversiKeMeter.put("m", 1.0);
            konversiKeMeter.put("dm", 0.1);
            konversiKeMeter.put("cm", 0.01);
            konversiKeMeter.put("mm", 0.001);
        }
        @Override
        public void start(Stage primaryStage) {
            Label inputLabel = new Label("Masukkan Nilai:");
            TextField inputField = new TextField();

            Label fromLabel = new Label("Dari:");
            ComboBox<String> fromUnit = new ComboBox<>();
            fromUnit.getItems().addAll(konversiKeMeter.keySet());

            Label toLabel = new Label("Ke:");
            ComboBox<String> toUnit = new ComboBox<>();
            toUnit.getItems().addAll(konversiKeMeter.keySet());

            Button convertButton = new Button("Konversi");
            convertButton.setStyle("-fx-background-color: #009688; -fx-text-fill: white;");
            Label resultLabel = new Label("Hasil: ");

            convertButton.setOnAction(e -> {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    String dari = fromUnit.getValue();
                    String ke = toUnit.getValue();

                    if (dari == null || ke == null) {
                        resultLabel.setText("Pilih satuan terlebih dahulu!");
                        return;
                    }

                    double dalamMeter = input * konversiKeMeter.get(dari);
                    double hasil = dalamMeter / konversiKeMeter.get(ke);

                    resultLabel.setText(String.format("Hasil: %.5f %s", hasil, ke));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Input tidak valid.");
                }
            });

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(15));
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setAlignment(Pos.CENTER);

            // 💡 Tambahkan style background setelah layout dibuat
            grid.setStyle("-fx-background-color: #e0f7fa;");
            grid.add(inputLabel, 0, 0);
            grid.add(inputField, 1, 0);
            grid.add(fromLabel, 0, 1);
            grid.add(fromUnit, 1, 1);
            grid.add(toLabel, 0, 2);
            grid.add(toUnit, 1, 2);
            grid.add(convertButton, 0, 3);
            grid.add(resultLabel, 1, 3);

            Scene scene = new Scene(grid, 350, 200);
            primaryStage.setTitle("Konversi Satuan Panjang");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }



