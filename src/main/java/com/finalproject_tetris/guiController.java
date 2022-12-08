package com.finalproject_tetris;

import com.logika.downData;
import com.logika.event.evenMove;
import com.logika.event.eventSource;
import com.logika.event.eventTipe;
import com.logika.event.inputEventListener;
import com.logika.tampilData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class guiController implements Initializable {
    private static final double BRICK_SIZE = 20;
    Timeline timeline;
    private inputEventListener eventListener;
    private Rectangle[][] displayMatrix;
    private Rectangle[][] rectangles;
    private BooleanProperty paused = new SimpleBooleanProperty();
    private BooleanProperty isGameOver = new SimpleBooleanProperty();

    @FXML
    private ToggleButton pausebutton;
    @FXML
    private GridPane gamePanel;
    @FXML
    private GridPane nextBrick;

    @FXML
    private GridPane brickPanel;

    @FXML
    private Text nilaiSkor;

    @FXML
    private Group groupNotifikasi;

    @FXML
    private BorderPane gameOver;


    @FXML
    void keluar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("tampilMenu.fxml"));
            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().setTitle("Menu Gapa Tetris");
        } catch (IOException e) {
            System.out.println("tidak bisa memuat halaman");
        }
    }

    public void initGameView(int[][] boardMatrix, tampilData tampilData){
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
       for (int i = 2; i < boardMatrix.length; i++){
           for (int j = 0; j< boardMatrix[i].length; j++){
               Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
               rectangle.setFill(Color.TRANSPARENT);
               displayMatrix[i][j] = rectangle;
               gamePanel.add(rectangle, j, i -2);
           }
       }
       rectangles = new Rectangle[tampilData.getBrickData().length][tampilData.getBrickData()[0].length];

       for (int i = 0; i < tampilData.getBrickData().length; i++){
           for (int j = 0; j < tampilData.getBrickData()[i].length; j++){
               Rectangle rectangle = new  Rectangle(BRICK_SIZE, BRICK_SIZE);
               rectangle.setFill(getFillColor(tampilData.getBrickData()[i][j]));
               rectangles[i][j] = rectangle;
               brickPanel.add(rectangle, j,i);
           }
       }
       brickPanel.setLayoutX(gamePanel.getLayoutX() + tampilData.getPosisix() * BRICK_SIZE);
       brickPanel.setLayoutY(-42 + gamePanel.getLayoutY()
               + (tampilData.getPosisiY() * BRICK_SIZE) + tampilData.getPosisiY());

       generatePreviewPanel(tampilData.getNextBrickData());

       timeline = new Timeline(new KeyFrame(Duration.millis(400),
               ae -> moveDown(new evenMove(eventTipe.DOWN, eventSource.THREAD))));
       timeline.setCycleCount(Timeline.INDEFINITE);
       timeline.play();
   }

   private void generatePreviewPanel(int[][] nextBrickData){
        nextBrick.getChildren().clear();
        for (int i = 0; i < nextBrickData.length; i++){
            for(int j = 0; j < nextBrickData[i].length; j++){
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangleData(nextBrickData[i][j], rectangle);
                if(nextBrickData[i][j] !=0 ){
                    nextBrick.add(rectangle, j, i);
                }
            }
        }
   }public void refreshGameBackground(int[][] board){
        for (int i = 2; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
   }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
//        rectangle.setStroke(Color.BLACK);
//
//        System.out.println(color);
//        if(color != 0){
//            rectangle.setStroke(Color.BLACK);
//            rectangle.setStrokeWidth(1);
//        }
    }

   public void bindScore(IntegerProperty integerProperty){
        nilaiSkor.textProperty().bind(integerProperty.asString());
   }

//   MEMBUAT BRICK KEBAWAH
    private void moveDown(evenMove event) {
        downData downData = eventListener.onDownEvent(event);

//        JIKA BERHASIL CLEAR BARIS MAKA MUNCULKAN NOTIFIKASI POINT
        if(downData.getClearBaris() != null &&downData.getClearBaris().getLinesRemoved() > 0){
            notifikasiBertambahController notifikasiBertambahController = new notifikasiBertambahController(" + " + downData.getClearBaris().getScoreBonus());
            groupNotifikasi.getChildren().add(notifikasiBertambahController);
            notifikasiBertambahController.showScore(groupNotifikasi.getChildren());
        }
        refreshBrick(downData.getTampilData());
    }

//    MENAMPILKAN BRICK SELANJUTNYA
    private void refreshBrick(tampilData tampilData) {
        brickPanel.setLayoutX(gamePanel.getLayoutX() + tampilData.getPosisix() * BRICK_SIZE);
        brickPanel.setLayoutY(-42 + gamePanel.getLayoutY()
                + (tampilData.getPosisiY() * BRICK_SIZE) + tampilData.getPosisiY());

        for (int i = 0; i < tampilData.getBrickData().length; i++){
            for (int j = 0; j < tampilData.getBrickData()[i].length; j++){
                setRectangleData(tampilData.getBrickData()[i][j], rectangles[i][j]);
            }
        }
        generatePreviewPanel(tampilData.getNextBrickData());
    }


    public void setEventListener(inputEventListener eventListener) {
        this.eventListener = eventListener;
    }


//    WARNA WARNA BRICK
    public Paint getFillColor(int i){
        Paint returnPaint;
        switch (i){
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = Color.rgb(255, 0, 0);
                break;
            case 2:
                returnPaint = Color.rgb(203, 1, 254);
                break;
            case 3:
                returnPaint = Color.rgb(0, 233, 1);
                break;
            case 4:
                returnPaint = Color.rgb(243, 244, 0);
                break;
            case 5:
                returnPaint = Color.rgb(254, 102, 0);
                break;
            case 6:
                returnPaint = Color.BLUE;
                break;
            case 7:
                returnPaint = Color.AQUA;
                break;
            default:
                returnPaint = Color.TRANSPARENT;
                break;
        }
        return returnPaint;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(paused.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE){
                    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W){
                        refreshBrick(eventListener.onRotateEvent());
                        event.consume();
                    }
                    if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S){
                        moveDown(new evenMove(eventTipe.DOWN, eventSource.USER));
                        event.consume();
                    }
                    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A){
                        refreshBrick(eventListener.onLeftEvent());
                        event.consume();
                    }
                    if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D){
                        refreshBrick(eventListener.onRightEvent());
                        event.consume();
                    }
                }
                if(event.getCode() == KeyCode.P){
                    pausebutton.selectedProperty().setValue(!pausebutton.selectedProperty().getValue());
                }
                if(event.getCode() == KeyCode.Q){
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

        });
        gameOver.setVisible(false);
        pausebutton.selectedProperty().bindBidirectional(paused);
        pausebutton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                Image imagePause = new Image(getClass().getResourceAsStream("img/pause.png"));
                Image imageResume =new Image(getClass().getResourceAsStream("img/pause.png"));
                if (newValue){
                    timeline.pause();
                } else {
                    timeline.play();
                }
            }
        });
    }

//    GAME OVER
    public void gameOverPanel(){
        timeline.stop();
        gameOver.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);
        System.out.println("Game Over!");
    }
}
