package com.demonhunts.throughit;

import com.badlogic.gdx.Game;
import com.demonhunts.throughit.screens.GameScreen;

public class ThroughIt extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
