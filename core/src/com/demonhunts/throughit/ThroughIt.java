package com.demonhunts.throughit;

import com.badlogic.gdx.Game;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.screens.GameScreen;

public class ThroughIt extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
    }
}
