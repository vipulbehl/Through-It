package com.demonhunts.throughit;

import com.badlogic.gdx.Game;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.helpers.PlayServices;
import com.demonhunts.throughit.screens.GameScreen;
import com.demonhunts.throughit.screens.MainScreen;

public class ThroughIt extends Game {

    public static PlayServices playServices;

    public ThroughIt(PlayServices playServices)
    {
        this.playServices = playServices;
    }

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
