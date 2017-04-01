package com.demonhunts.throughit.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.demonhunts.throughit.gameobjects.ScrollHandler;
import com.demonhunts.throughit.gameobjects.Dot;
import com.demonhunts.throughit.helpers.AssetLoader;

public class GameWorld {

    private Dot dot;
    private ScrollHandler scroller;
    private Rectangle ground;
    private Rectangle ceiling;
    private int score;
    public int midPointY;

    private GameState currentState;

    public enum GameState {
        READY,RUNNING,GAMEOVER
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        dot = new Dot(33,midPointY-9,17,17);
        scroller = new ScrollHandler(this,midPointY + 66);
        ground = new Rectangle(0,midPointY*2-11,136,11);
        ceiling = new Rectangle(0,0,136,0);
        this.midPointY = midPointY;
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
        }

    }

    private void updateReady(float delta) {

    }

    public void updateRunning(float delta){
        if(delta > .15f){
            delta = .15f;
        }

        dot.update(delta);
        scroller.update(delta);

        if (scroller.collides(dot)) {
            scroller.stop();
            dot.die();
            currentState = GameState.GAMEOVER;
        }

        if(Intersector.overlaps(dot.getBoundingCircle(),ground)){
            scroller.stop();
            dot.die();
            currentState = GameState.GAMEOVER;
        }

        if(Intersector.overlaps(dot.getBoundingCircle(),ceiling)){
            scroller.stop();
            dot.die();
            currentState = GameState.GAMEOVER;
        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        dot.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int increment){
        score = score + increment;
        if(AssetLoader.getHighScore() < score){
            AssetLoader.setHighScore(score);
        }
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public void setScroller(ScrollHandler scroller) {
        this.scroller = scroller;
    }

    public Dot getDot() {
        return dot;
    }
}
