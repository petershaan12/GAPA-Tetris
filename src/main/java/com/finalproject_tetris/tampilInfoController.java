package com.finalproject_tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class tampilInfoController {
    @FXML
    private StackPane rootPane;

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("tampilMenu.fxml"));
            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().setTitle("Menu Gapa Tetris");
        } catch (IOException e) {
            System.out.println("tidak bisa memuat halaman");
        }
    }

}


