package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass,playButtonUp,playButtonDown;

    public static FreeTypeFontGenerator generator;
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter,parameter1;
    public static BitmapFont fontS,fontB;

    public static TextureAtlas atlas,buttonAtlas;

    public static TextureRegion dot;
    public static TextureRegion bar;

    public static Preferences prefs;

    public static void load() {

        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Play-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BROWN;
        parameter.size = 15;
        fontB = generator.generateFont(parameter);

        parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.color = Color.BROWN;
        parameter1.size = 12;
        fontS = generator.generateFont(parameter1);

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

    }

    public static void dispose() {
        fontS.dispose();
        fontB.dispose();
        texture.dispose();
    }

    public static void setHighScore(int score){
        prefs.putInteger("highScore",score);
        prefs.flush();
    }

    public static int getHighScore(){
        return prefs.getInteger("highScore");
    }
}
