package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetLoader {
    public static TextureRegion bg, grass;

    public static FreeTypeFontGenerator generator;
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter,parameter1,parameter2;
    public static BitmapFont fontS,fontB,fontGame;

    public static TextureAtlas atlas,buttonAtlas;

    public static TextureRegion dot;
    public static TextureRegion bar;

    public static Preferences prefs;

    public static Sound passSound,clickSound,gameOverSound;

    public static void load() {

        generator = new FreeTypeFontGenerator(Gdx.files.internal("Play-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BROWN;
        parameter.size = 105;
        fontB = generator.generateFont(parameter);

        parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.color = Color.BROWN;
        parameter1.size = 80;
        fontS = generator.generateFont(parameter1);


        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.color = Color.BROWN;
        parameter2.size = 20;
        fontGame = generator.generateFont(parameter2);

        buttonAtlas = new TextureAtlas("buttons.pack");
        atlas = new TextureAtlas("images.pack");
        dot = atlas.findRegion("ball");
        dot.flip(false, true);


        bg = atlas.findRegion("background");
        bg.flip(false, true);

        grass = atlas.findRegion("bottom");
        grass.flip(false, true);

        bar = atlas.findRegion("pipe1");
        bar.flip(false, true);

        prefs = Gdx.app.getPreferences("ThroughIt");
        if(!prefs.contains("highScore")){
            prefs.putInteger("highScore",0);
        }

        passSound = Gdx.audio.newSound(Gdx.files.internal("passSound.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.wav"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("gameOverSound.wav"));
    }

    public static void dispose() {
        fontS.dispose();
        fontB.dispose();
    }

    public static void setHighScore(int score){
        prefs.putInteger("highScore",score);
        prefs.flush();
    }

    public static int getHighScore(){
        return prefs.getInteger("highScore");
    }
}
