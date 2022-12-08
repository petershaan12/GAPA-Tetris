module com.finalproject_tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.finalproject_tetris to javafx.fxml;
    exports com.finalproject_tetris;
}