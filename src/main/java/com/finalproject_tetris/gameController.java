package com.finalproject_tetris;

import com.bricks.*;
import com.logika.boardSimple;
import com.logika.clearBaris;
import com.logika.downData;
import com.logika.event.evenMove;
import com.logika.event.eventSource;
import com.logika.event.inputEventListener;
import com.logika.tampilData;

public class gameController implements inputEventListener {

    private boardSimple board = new boardSimple(10, 25);
    private final guiController viewController;

    public gameController(guiController c){
        this.viewController = c;
        this.viewController.setEventListener(this);
        board.membuatNewBrick();
        this.viewController.initGameView(board.getBoardMatrix(), board.getTampilData());
        this.viewController.bindScore(board.getSkor().skorProperty());
    }

    @Override
    public downData onDownEvent(evenMove event) {
        boolean bisaGerak = board.moveBrickDown();
        clearBaris clear = null;
        if(!bisaGerak){
            board.mergeBrickToBackground();
            clear = board.clearBaris();
            if(clear.getLinesRemoved() > 0 ){
                board.getSkor().add(clear.getScoreBonus());
            }
            if(board.membuatNewBrick()){
                viewController.gameOverPanel();
            }
        }
        viewController.refreshGameBackground(board.getBoardMatrix());
        return new downData(clear, board.getTampilData());
    }

    @Override
    public tampilData onLeftEvent(){
        board.moveBrickLeft();
        return board.getTampilData();
    }
    @Override
    public tampilData onRightEvent(){
        board.moveBrickRight();
        return board.getTampilData();
    }
    @Override
    public tampilData onRotateEvent(){
        board.rotateBrickLeft();
        return board.getTampilData();
    }
}
