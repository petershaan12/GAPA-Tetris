package com.logika;

public class tampilData {

    private final int[][] nextBrickData;
    private final int[][] brickData;
    private final int posisix;
    private final int posisiY;

    //membuat constructornya
    public tampilData(int[][] brickData, int posisix, int posisiY, int[][] nextBrickData) {
        this.brickData = brickData;
        this.posisix = posisix;
        this.posisiY = posisiY;
        this.nextBrickData = nextBrickData;
    }

    //membuat getter

    public int[][] getNextBrickData() {
        return nextBrickData;
    }

    public int[][] getBrickData() {
        return brickData;
    }

    public int getPosisix() {
        return posisix;
    }

    public int getPosisiY() {
        return posisiY;
    }
}
