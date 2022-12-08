package com.bricks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//Disini kita membuat brick brick yang kita sudah buat akan tampil secara random

public class membuatRandomBrick {

    private final List<Brick> brickList;
    private final Deque<Brick> nextBricks = new ArrayDeque<>();
    public membuatRandomBrick(){
        brickList = new ArrayList<>();
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());

        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
        nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
    }
//    MENAMPILKAN SETELAH BRICK YG AKAN MUNCUL SELANJUTNYA
    public Brick getNextBrick(){
        return nextBricks.peek();
    }

//    MEMBUAT GETTER
    public Brick getBrick(){
        if(nextBricks.size() <= 1){
            nextBricks.add(brickList.get(ThreadLocalRandom.current().nextInt(brickList.size())));
        }
        return nextBricks.poll();
    }
}
