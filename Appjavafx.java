package com.example.appjavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
    public class Appjavafx extends Application {

        @Override
        public void start(Stage primaryStage) {
            // Membuat label awal
            Label label = new Label("Halo, Selamat Datang");


            // Membuat tombol
            Button button = new Button("Tekan Saya");
            button.setStyle("-fx-background-color: #009688; -fx-text-fill: white;");


            // Event ketika tombol ditekan
            button.setOnAction(e -> label.setText("Tombol ditekan!"));

            // Menyusun komponen ke dalam layout VBox
            VBox layout = new VBox(10); // 10 px spacing
            layout.getChildren().addAll(label, button);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-color:  #e0f7fa;");
            // Membuat scene dan menampilkannya
            Scene scene = new Scene(layout, 300, 200);
            primaryStage.setTitle("JavaFX - Label & Tombol");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args); // Meluncurkan aplikasi JavaFX
        }
    }

