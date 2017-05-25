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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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

public class EndScreen implements Screen,InputProcessor {
    final ThroughIt game;
    int score;
    final float appWidth = 136;
    final float appHeight = 204;
    SpriteBatch batch;
    OrthographicCamera camera;
    Sound clickSound;
    Preferences prefs;

    private String scoreString;
    private String highScoreString;

    GlyphLayout layoutGameOver,layoutYourScore,layoutHighScore;

    private Stage stage;
    private Skin buttonSkin;
    private TextureAtlas buttonAtlas;
    private ImageButton playButton,leaderboardButton,achievementsButton,homeButton;

    public EndScreen(final ThroughIt gam, int score){
        this.game=gam;
        this.score = score;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, appWidth, appHeight);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        buttonAtlas = new TextureAtlas("buttons.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        stage = new Stage(new FillViewport(appWidth,appHeight));
        stage.clear();
        InputMultiplexer plex = new InputMultiplexer();
        plex.addProcessor(stage);
        plex.addProcessor(this);
        Gdx.input.setInputProcessor(plex);
        prefs = Gdx.app.getPreferences("My Preferences");
//        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clickSound.mp3"));

        scoreString = "Score : "+score;
        highScoreString = "Best : "+AssetLoader.getHighScore();

        if(game.isPlayServices)
            game.playServices.submitScore(score);

        layoutGameOver = new GlyphLayout();
        layoutGameOver.setText(AssetLoader.fontB,"Game Over");

        layoutYourScore = new GlyphLayout();
        layoutYourScore.setText(AssetLoader.fontS,scoreString);

        layoutHighScore = new GlyphLayout();
        layoutHighScore.setText(AssetLoader.fontS,highScoreString);


        //Play Button resources
        playButton = new ImageButton(buttonSkin.getDrawable("play"),buttonSkin.getDrawable("playClicked"));
        playButton.setSize(50,20);
        playButton.setPosition(appWidth/2-playButton.getWidth()/2,appHeight/2-playButton.getHeight()/2);
        playButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
//                if(prefs.getBoolean("soundOn",true))
//                    clickSound.play();
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(playButton);

        //Leaderboard Button resources
        leaderboardButton = new ImageButton(buttonSkin.getDrawable("leaderboard"),buttonSkin.getDrawable("leaderboardClicked"));
        leaderboardButton.setSize(50,20);
        leaderboardButton.setPosition(widthPercent(30)-leaderboardButton.getWidth()/2,heightPercent(30));
        leaderboardButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
//                if(prefs.getBoolean("soundOn",true))
//                    clickSound.play();
                if(game.isPlayServices)
                    game.playServices.showScore();
            }
        });
        if(game.isPlayServices)
            stage.addActor(leaderboardButton);

        //Achievements Button resources
        achievementsButton = new ImageButton(buttonSkin.getDrawable("achievements"),buttonSkin.getDrawable("achievementsClicked"));
        achievementsButton.setSize(50,20);
        achievementsButton.setPosition(widthPercent(70)-achievementsButton.getWidth()/2,heightPercent(30));
        achievementsButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
//                if(prefs.getBoolean("soundOn",true))
//                    clickSound.play();
                if(game.isPlayServices)
                    game.playServices.showAchievement();
            }
        });
        if(game.isPlayServices)
            stage.addActor(achievementsButton);

        //Home Button resources
        homeButton = new ImageButton(buttonSkin.getDrawable("home"),buttonSkin.getDrawable("homeClicked"));
        homeButton.setSize(50,20);
        homeButton.setPosition(appWidth/2-homeButton.getWidth()/2, heightPercent(15));
        homeButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
//                if(prefs.getBoolean("soundOn",true))
//                    clickSound.play();
                game.setScreen(new MainScreen(game));
            }
        });
        stage.addActor(homeButton);
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
        AssetLoader.fontB.draw(batch,"GAME OVER",appWidth/2-layoutGameOver.width/2,heightPercent(90));
        AssetLoader.fontS.draw(batch,scoreString,appWidth/2-layoutYourScore.width/2,heightPercent(80));
        AssetLoader.fontS.draw(batch,highScoreString,appWidth/2-layoutHighScore.width/2,heightPercent(70));
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        buttonSkin.dispose();
        buttonAtlas.dispose();
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
        if(keycode == Input.Keys.BACK){
            game.setScreen(new MainScreen(game));
        }
        return true;
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
