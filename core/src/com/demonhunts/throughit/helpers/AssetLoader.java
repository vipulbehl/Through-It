package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass,playButtonUp,playButtonDown;
    public static BitmapFont font;

    public static TextureAtlas atlas;

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


        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(.50f,-.50f);

        atlas = new TextureAtlas("images.pack");
        dot = atlas.findRegion("dot");
        //dot = new TextureRegion(texture, 153, 0, 17, 12);
        dot.flip(false, true);

        bg = atlas.findRegion("back");
        //bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
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
