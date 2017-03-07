package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass;
    public static BitmapFont font;

    public static TextureRegion dot;
    public static TextureRegion bar;

    public static void load() {

        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(.50f,-.50f);

        dot = new TextureRegion(texture, 153, 0, 17, 12);
        dot.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

    }

    public static void dispose() {
        font.dispose();
        texture.dispose();
    }
}
