package com.demonhunts.throughit.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.screens.GameScreen;
import com.demonhunts.throughit.helpers.FontHelper;
import com.badlogic.gdx.Game;




public class MainScreen extends Game implements Screen {


    @Override
    public void create() {

    }

    public static TextureAtlas atlas;
    public static TextureRegion playbutton,bg,tr;
    public static TextureRegionDrawable myTexRegionDrawable;
    private FontHelper fontHelper;
    private ImageButton button;
    private Skin buttonSkin;


    @Override
    public void show() {

        final SpriteBatch batch = new SpriteBatch();
        batch.begin();
        atlas = new TextureAtlas("images.pack");

        bg = atlas.findRegion("back");
        bg.flip(false, true);
        batch.draw(bg, 0, 0, 272, 408);
        BitmapFont font = new BitmapFont();
        font.draw(batch, "Through It!", 100, 350);
        fontHelper = new FontHelper();


        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight/(screenWidth/gameWidth);
        int midPoint = (int) gameHeight/2;
        playbutton = atlas.findRegion("playButton");

        batch.draw(playbutton,68,midPoint);





         tr = new TextureRegion(playbutton);

        myTexRegionDrawable = new TextureRegionDrawable(tr);
        button = new ImageButton(myTexRegionDrawable);


        button.addListener(new ClickListener()
        {

            @Override
            public void clicked(InputEvent event, float x, float y) {

                setScreen(new GameScreen());
            }
        });


        batch.end();











    }

    @Override
    public void render(float delta) {
            show();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
