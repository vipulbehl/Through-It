package com.demonhunts.throughit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.demonhunts.throughit.ThroughIt;
import com.demonhunts.throughit.helpers.AssetLoader;

public class MainScreen implements Screen,InputProcessor {
    final ThroughIt game;
    final float appWidth = 768;
    final float appHeight = 1280;
    SpriteBatch batch;
    OrthographicCamera camera;
    Sound clickSound;
    Preferences prefs;
    private int backCounter;

    private Stage stage;
    private Skin buttonSkin;
    private TextureAtlas buttonAtlas;
    private ImageButton playButton,leaderboardButton,achievementsButton,soundButton,rateButton,info;

    public MainScreen(final ThroughIt gam){
        this.game=gam;
        backCounter = 0;

        if(game.isPlayServices)
            game.playServices.signIn();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, appWidth, appHeight);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        buttonAtlas = AssetLoader.buttonAtlas;
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        stage = new Stage(new FillViewport(appWidth,appHeight));
        stage.clear();

        InputMultiplexer plex = new InputMultiplexer();
        plex.addProcessor(this);
        plex.addProcessor(stage);
        Gdx.input.setInputProcessor(plex);
        prefs = Gdx.app.getPreferences("My Preferences");
        clickSound = game.clickSound;

        //Play Button resources
        playButton = new ImageButton(buttonSkin.getDrawable("play"),buttonSkin.getDrawable("playClicked"));
        playButton.setPosition(appWidth/2-playButton.getWidth()/2,heightPercent(42));
        playButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(prefs.getBoolean("soundOn",true))
                    clickSound.play();
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(playButton);


        //Leaderboard Button resources
        leaderboardButton = new ImageButton(buttonSkin.getDrawable("leaderboard"),buttonSkin.getDrawable("leaderboardClicked"));
        leaderboardButton.setPosition(widthPercent(30)-leaderboardButton.getWidth()/2,heightPercent(30));
        leaderboardButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(prefs.getBoolean("soundOn",true))
                    clickSound.play();
                if(game.isPlayServices)
                    game.playServices.showScore();
            }
        });

        if(game.isPlayServices)
            stage.addActor(leaderboardButton);

        //Achievements Button resources
        achievementsButton = new ImageButton(buttonSkin.getDrawable("achievements"),buttonSkin.getDrawable("achievementsClicked"));
        achievementsButton.setPosition(widthPercent(70)-achievementsButton.getWidth()/2,heightPercent(30));
        achievementsButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(prefs.getBoolean("soundOn",true))
                    clickSound.play();
                if(game.isPlayServices)
                    game.playServices.showAchievement();
            }
        });
        if(game.isPlayServices)
            stage.addActor(achievementsButton);

        //Rate Button Resource
        rateButton = new ImageButton(buttonSkin.getDrawable("rate"),buttonSkin.getDrawable("rate"));
        rateButton.setPosition(widthPercent(53),heightPercent(20));
        rateButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(prefs.getBoolean("soundOn",true))
                    clickSound.play();
                if(game.isPlayServices)
                    game.playServices.rateGame();
            }
        });
        stage.addActor(rateButton);

        //Sound Button resources
        if(prefs.getBoolean("soundOn",true))
            soundButton = new ImageButton(buttonSkin.getDrawable("soundEnable"),buttonSkin.getDrawable("soundDisable"),
                    buttonSkin.getDrawable("soundDisable"));
        else
            soundButton = new ImageButton(buttonSkin.getDrawable("soundDisable"),buttonSkin.getDrawable("soundEnable"),
                    buttonSkin.getDrawable("soundEnable"));

        soundButton.setPosition(widthPercent(37),heightPercent(20));
        soundButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(prefs.getBoolean("soundOn",true)){
                    clickSound.play();
                    prefs.putBoolean("soundOn",false);
                    soundButton.setChecked(true);
                    soundButton.setBackground(buttonSkin.getDrawable("soundDisable"));
                }
                else{
                    prefs.putBoolean("soundOn",true);
                    soundButton.setChecked(false);
                    soundButton.setBackground(buttonSkin.getDrawable("soundEnable"));
                }
                prefs.flush();
            }
        });
        stage.addActor(soundButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        batch.begin();
        stage.draw();
        batch.end();

        batch.begin();
        AssetLoader.fontB.draw(batch,"Through It",appWidth/6,heightPercent(70));
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        buttonSkin.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,false);
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

    public float widthPercent(int w){
        float result;
        result = (appWidth*w)/100;
        return result;
    }

    public float heightPercent(int h){
        float result;
        result = (appHeight*h)/100;
        return result;
    }


    @Override
    public boolean keyDown(int keycode) {
//        if(keycode == Input.Keys.BACK && backCounter ==2){
//            Gdx.app.exit();
//        }
//        else{
//            backCounter++;
//        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}