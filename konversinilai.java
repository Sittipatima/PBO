package com.example.appjavafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class konversinilai extends Application{
        @Override
        public void start(Stage primaryStage) {
            Label angkaLabel = new Label("Nilai Angka (0-100):");
            angkaLabel.setStyle("-fx-text-fill: #006064;"); // 💡 Tambahkan setelah membuat label

            TextField angkaField = new TextField();

            Label hurufLabel = new Label("Nilai Huruf:");
            TextField hurufField = new TextField();

            Button keHurufButton = new Button("Konversi ke Huruf");
            keHurufButton.setStyle("-fx-background-color: #009688; -fx-text-fill: white;"); // 💡 Tambahkan setelah buat button
            Button keAngkaButton = new Button("Konversi ke Angka");

            Label hasilLabel = new Label("Hasil: ");
            hasilLabel.setStyle("-fx-background-color: #e0f7fa;");

            // Angka ke Huruf
            keHurufButton.setOnAction(e -> {
                try {
                    int nilai = Integer.parseInt(angkaField.getText());
                    String huruf = konversiKeHuruf(nilai);
                    hasilLabel.setText("Nilai Huruf: " + huruf);
                } catch (NumberFormatException ex) {
                    hasilLabel.setText("Masukkan angka valid.");
                }
            });

            // Huruf ke Angka (estimasi)
            keAngkaButton.setOnAction(e -> {
                String huruf = hurufField.getText().toUpperCase().trim();
                Integer rataRata = konversiKeAngka(huruf);
                if (rataRata == null) {
                    hasilLabel.setText("Nilai huruf tidak dikenal.");
                } else {
                    hasilLabel.setText("Nilai Angka Estimasi: " + rataRata);
                }
            });

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(15));
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setAlignment(Pos.CENTER);

            // 💡 Tambahkan style background setelah layout dibuat
            grid.setStyle("-fx-background-color: #e0f7fa;");
            grid.add(angkaLabel, 0, 0);
            grid.add(angkaField, 1, 0);
            grid.add(keHurufButton, 2, 0);
            grid.add(hurufLabel, 0, 1);
            grid.add(hurufField, 1, 1);
            grid.add(keAngkaButton, 2, 1);

            grid.add(hasilLabel, 1, 2);

            Scene scene = new Scene(grid, 500, 200);
            primaryStage.setTitle("Konversi Nilai Kuliah");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    private String konversiKeHuruf(int nilai) {
        if (nilai >= 85) return "A";
        else if (nilai >= 80) return "A-";
        else if (nilai >= 75) return "B+";
        else if (nilai >= 70) return "B";
        else if (nilai >= 65) return "B-";
        else if (nilai >= 60) return "C";
        else if (nilai >= 50) return "D";
        else return "E";
    }

    private Integer konversiKeAngka(String huruf) {
        return switch (huruf) {
            case "A" -> 92;
            case "A-" -> 82;
            case "B+" -> 77;
            case "B" -> 72;
            case "B-" -> 67;
            case "C" -> 62;
            case "D" -> 55;
            case "E" -> 40;
            default -> null;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}


