package com.logika;

import java.awt.Point;

import com.bricks.Brick;
import com.bricks.membuatRandomBrick;

public class boardSimple {

    private final int lebar;
    private final int tinggi;
    private int[][] GameMatrix;
    private final membuatRandomBrick brickGenerator;
    private Brick brick;
    private int currentShape = 0;
    private Point currentOffset;
    private skor skor;

    //constructor
    public boardSimple(int lebar, int tinggi){
        this.lebar = lebar;
        this.tinggi = tinggi;
        GameMatrix = new int[tinggi][lebar];
        brickGenerator = new membuatRandomBrick();
        skor = new skor();
    }

    public boolean membuatNewBrick(){
        currentShape = 0;
        Brick currentBrick = brickGenerator.getBrick();
        setBrick(currentBrick );
        currentOffset = new Point(4, 0);
        return operasiMatrix.intersects(GameMatrix, getCurrentShape(),
                currentOffset.x,
                currentOffset.y);
    }

    public skor getSkor() {
        return skor;
    }

    public boolean moveBrickDown(){
        Point p = new Point(currentOffset);
        p.translate(0,1);
        currentOffset = p;
        boolean conflict = operasiMatrix.intersects(GameMatrix, getCurrentShape(), p.x, p.y);
        if(conflict){
            return false;
        } else {
            currentOffset = p;
            return true;
        }
    }
    public boolean moveBrickLeft() {
        Point p = new Point(currentOffset);
        p.translate(-1,0);
        boolean conflict = operasiMatrix.intersects(GameMatrix, getCurrentShape(), p.x, p.y);
        if(conflict){
            return false;
        } else {
            currentOffset = p;
            return true;
        }
    }
    public boolean moveBrickRight() {
        Point p = new Point(currentOffset);
        p.translate(1,0);
        boolean conflict = operasiMatrix.intersects(GameMatrix, getCurrentShape(), p.x, p.y);
        if(conflict){
            return false;
        } else {
            currentOffset = p;
            return true;
        }
    }

    public tampilData getTampilData(){
        return new tampilData(getCurrentShape(),
                currentOffset.x, currentOffset.y,
                brickGenerator.getNextBrick().getBrickMatrix().get(0));

    }

    //getter current shape
    public int[][] getCurrentShape(){
        return this.brick.getBrickMatrix().get(currentShape);
    }

//    MEMBUAT GETTER DAN SETTER
    public void setBrick(Brick brick) {
        this.brick = brick;
        currentOffset = new Point(4, 0);
    }
    public int[][] getBoardMatrix(){
        return GameMatrix;
    }


    public void mergeBrickToBackground() {
        GameMatrix = operasiMatrix.merge(GameMatrix,
                getCurrentShape(),
                currentOffset.x,
                currentOffset.y);

    }
    public infoShapeSelanjutanya getNextShape(){
        int nextShape = currentShape;
        nextShape = ++nextShape % brick.getBrickMatrix().size();
        return new infoShapeSelanjutanya(brick.getBrickMatrix().get(nextShape), nextShape);
    }

    public boolean rotateBrickLeft() {
        infoShapeSelanjutanya nextShape = getNextShape();
        boolean conflict = operasiMatrix.intersects(GameMatrix, nextShape.getShape(), currentOffset.x, currentOffset.y);
        if (conflict){
            return false;
        } else {
            setCurrentShape(nextShape.getPosition());
            return true;
        }
    }

    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    public clearBaris clearBaris() {
        clearBaris clearBaris = operasiMatrix.checkRemoving(GameMatrix);
        GameMatrix = clearBaris.getNextMatrix();
        return clearBaris;
    }
}
