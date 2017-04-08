package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.demonhunts.throughit.gameobjects.Dot;
import com.demonhunts.throughit.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

    private Dot myDot;
    private GameWorld myWorld;
    private Rectangle playRectangle;
    private int gameHeight;
    private int gameWidth;


    public InputHandler(GameWorld myWorld){
        this.myWorld = myWorld;
        myDot = myWorld.getDot();
        gameHeight = Gdx.graphics.getHeight();
        gameWidth = Gdx.graphics.getWidth();
        playRectangle = new Rectangle(80,gameHeight-165,20,20);
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
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Gdx.app.log("Touch Position","X : "+screenX+"  -- Y : "+screenY);
            Gdx.app.log("Rectangle","X : "+playRectangle.getX()+"  -- Y : "+playRectangle.getY());
            if(playRectangle.contains(touchPos.x,touchPos.y))
                myWorld.restart();
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
