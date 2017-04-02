package com.demonhunts.throughit;

import com.badlogic.gdx.Game;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.screens.GameScreen;
import com.demonhunts.throughit.screens.MainScreen;

public class ThroughIt extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        this.setScreen(new MainScreen(this));
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
    }
}
