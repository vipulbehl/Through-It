package com.demonhunts.throughit.gameobjects;

import com.demonhunts.throughit.gameworld.GameWorld;
import com.demonhunts.throughit.helpers.AssetLoader;

public class ScrollHandler {

    private Pipe pipe1, pipe2, pipe3;
    public static final int SCROLL_SPEED = -80;
    public static final int PIPE_GAP = 109;
    public GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld,float yPos) {
        this.gameWorld = gameWorld;
        pipe1 = new Pipe(150, 0, 22, 60, SCROLL_SPEED, yPos);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED,
                yPos);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED,
                yPos);
    }

    public void update(float delta) {
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }
    }

    public void stop() {
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    public void onRestart() {
        pipe1.onRestart(210, SCROLL_SPEED);
        pipe2.onRestart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
        pipe3.onRestart(pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
    }

    public boolean collides(Dot dot) {
        if (!pipe1.isScored()
                && pipe1.getX() + (pipe1.getWidth() / 2) < dot.getX()
                + dot.getWidth()) {
            addScore(1);
            pipe1.setScored(true);
        } else if (!pipe2.isScored()
                && pipe2.getX() + (pipe2.getWidth() / 2) < dot.getX()
                + dot.getWidth()) {
            addScore(1);
            pipe2.setScored(true);

        } else if (!pipe3.isScored()
                && pipe3.getX() + (pipe3.getWidth() / 2) < dot.getX()
                + dot.getWidth()) {
            addScore(1);
            pipe3.setScored(true);

        }

        return (pipe1.collides(dot) || pipe2.collides(dot) || pipe3
                .collides(dot));
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

}