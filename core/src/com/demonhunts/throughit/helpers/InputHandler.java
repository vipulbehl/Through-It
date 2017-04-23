package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.InputProcessor;
import com.demonhunts.throughit.gameobjects.Dot;
import com.demonhunts.throughit.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

    private Dot myDot;
    private GameWorld myWorld;


    public InputHandler(GameWorld myWorld){
        this.myWorld = myWorld;
        myDot = myWorld.getDot();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isReady()) {
            myWorld.start();
        }

        myDot.onClick();

        if (myWorld.isGameOver()) {
            //myWorld.restart();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



}
