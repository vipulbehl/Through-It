package com.demonhunts.throughit.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.demonhunts.throughit.gameobjects.Dot;

public class GameWorld {

    private Dot dot;

    public GameWorld(int midPointY) {
        dot = new Dot(33,midPointY-9,17,17);
    }

    public void update(float delta){
        dot.update(delta);
    }

    public Dot getDot() {
        return dot;
    }
}
