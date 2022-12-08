package com.logika;

public class infoShapeSelanjutanya {
    private final int[][] shape;
    private final int position;

    public infoShapeSelanjutanya(int[][] shape, int position){
        this.shape = shape;
        this.position = position;
    }
    public int[][] getShape(){
        return shape;
    }
    public int getPosition(){
        return position;
    }
}
