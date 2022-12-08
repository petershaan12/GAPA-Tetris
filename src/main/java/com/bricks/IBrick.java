package com.bricks;

import java.util.ArrayList;
import java.util.List;

public class IBrick implements Brick {

    private final List<int[][]> brickMatrix = new ArrayList<>();

    public List<int[][]> getBrickMatrix() {
        return brickMatrix;
    }

    public IBrick(){
        brickMatrix.add(new int[][]{
                {0,0,0,0},
                {2,2,2,2},
                {0,0,0,0},
                {0,0,0,0}
        });

        brickMatrix.add(new int[][]{
                {0,2,0,0},
                {0,2,0,0},
                {0,2,0,0},
                {0,2,0,0}
        });
    }


}
