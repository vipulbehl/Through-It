package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass,playButtonUp,playButtonDown;
    public static BitmapFont font,endfont;

    public static TextureAtlas atlas,buttonAtlas;

    public static TextureRegion dot,playbutton;
    public static TextureRegion bar;

    public static Preferences prefs;

    public static void load() {

        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);


        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f,-.5f);

        endfont = new BitmapFont(Gdx.files.internal("text.fnt"),true);
        endfont.setColor(Color.BLACK);
        endfont.getData().setScale(0.5f,-.5f);

        buttonAtlas = new TextureAtlas("buttons.pack");
        atlas = new TextureAtlas("images.pack");
        dot = atlas.findRegion("ball");
        dot.flip(false, true);

        playbutton = atlas.findRegion("playButton");


        bg = atlas.findRegion("back");
        bg.flip(false, true);

        grass = atlas.findRegion("bottom");
        grass.flip(false, true);

//        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar = atlas.findRegion("pipe1");
        bar.flip(false, true);

        prefs = Gdx.app.getPreferences("ThroughIt");
        if(!prefs.contains("highScore")){
            prefs.putInteger("highScore",0);
        }




    }

    public static void dispose() {
        font.dispose();
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
