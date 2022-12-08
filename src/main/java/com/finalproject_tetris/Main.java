package com.finalproject_tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //MEMBUAT GETTER STAGE
    //supaya bisa dipakai setelah tampilan awal (splash screen dari game) tanpa harus membuat window/stage baru
    private static Stage guiStage;
    public static Stage getStage() {
        return guiStage;
    }

    //Yang Akan dieksekusi
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tampilAwal.fxml"));
        Scene scene = new Scene(root);
        //ICON
        Image icon = new Image(getClass().getResourceAsStream("img/LOGO2.png"));
        guiStage = primaryStage;
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("LOADING....");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}