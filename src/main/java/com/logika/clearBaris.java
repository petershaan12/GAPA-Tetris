package com.logika;

public class clearBaris {

    private final int scoreBonus;
    private final int linesRemoved;
    private final int[][] nextMatrix;
    public clearBaris(int linesRemoved, int[][] nextMatrix, int scoreBonus){
        this.linesRemoved = linesRemoved;
        this.nextMatrix = nextMatrix;
        this.scoreBonus = scoreBonus;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public int getScoreBonus() {
        return scoreBonus;
    }

    public int[][] getNextMatrix() {
        return nextMatrix;
    }
}
