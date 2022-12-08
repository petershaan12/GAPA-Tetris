package com.bricks;

import java.util.ArrayList;
import java.util.List;

public class ZBrick implements Brick {

    private final List<int[][]> brickMatrix = new ArrayList<>();

    public ZBrick() {
        brickMatrix.add(new int[][] {
                {0, 0, 0, 0},
                {7, 7, 0, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][] {
                {0, 7, 0, 0},
                {7, 7, 0, 0},
                {7, 0, 0, 0},
                {0, 0, 0, 0}
        });
    }

    public List<int[][]> getBrickMatrix() {
        return brickMatrix;
    }

}

