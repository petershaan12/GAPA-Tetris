package com.bricks;

import java.util.ArrayList;
import java.util.List;

public class OBrick implements Brick {
    private final List<int[][]> brickMatrix = new ArrayList<>();

    public OBrick(){
        brickMatrix.add(new int[][] {
                {0,0,0,0},
                {0,1,1,0},
                {0,1,1,0},
                {0,0,0,0}
        });
    }

    public List<int[][]> getBrickMatrix(){
        return brickMatrix;
    }
}
