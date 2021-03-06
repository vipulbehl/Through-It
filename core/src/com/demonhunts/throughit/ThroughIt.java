package com.demonhunts.throughit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.helpers.PlayServices;
import com.demonhunts.throughit.screens.GameScreen;
import com.demonhunts.throughit.screens.MainScreen;
import com.demonhunts.throughit.screens.SplashScreen;

public class ThroughIt extends Game {

    public static PlayServices playServices;
    public static boolean isPlayServices;
    public static Sound clickSound,passSound,gameOverSound;

    public ThroughIt(PlayServices playServices)
    {
        this.playServices = playServices;
        this.isPlayServices = true;
    }

    public ThroughIt(boolean isPlayServices){
        this.isPlayServices = isPlayServices;
    }



    @Override
    public void create() {
        AssetLoader.load();
        clickSound = AssetLoader.clickSound;
        passSound = AssetLoader.passSound;
        this.setScreen(new MainScreen(this));
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
    }
}
