package com.demonhunts.throughit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.demonhunts.throughit.ThroughIt;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.helpers.FontHelper;

public class EndScreen implements Screen, InputProcessor {

    private final ThroughIt game;

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private Stage stage;

    private Skin buttonSkin;
    private ImageButton playButton;

    private int score;
    private TextureRegion bg;
    private FontHelper fontHelper;

    public EndScreen(final ThroughIt game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(true,136,204);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        buttonSkin = new Skin();
        buttonSkin.addRegions(AssetLoader.atlas);

        bg = AssetLoader.bg;

        stage = new Stage();
        stage.clear();

        fontHelper = new FontHelper();
        Gdx.input.setInputProcessor(stage);

        //Play Button Resources
        playButton = new ImageButton(buttonSkin.getDrawable("playButton"),buttonSkin.getDrawable("playButtonClicked"));
        playButton.setSize(30,30);
        playButton.setPosition(Gdx.graphics.getWidth()/2-30,95);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(playButton);
    }

    public void setScore(int score){
        this.score = score;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.draw(bg,0,0);
        AssetLoader.font.draw(batcher, "Game Over", fontHelper.centerAlign("Game Over"), 35);
        AssetLoader.font.draw(batcher, "Try again?", fontHelper.centerAlign("Try again?"), 55);
        AssetLoader.font.draw(batcher,"HighScore",fontHelper.centerAlign("HighScore"),75);
        String highScore = AssetLoader.getHighScore()+"";
        int highScoreWidth = (136/2)+(fontHelper.getWidth("HighScore")/2)+10;
        AssetLoader.font.draw(batcher, highScore, highScoreWidth, 75);
        batcher.end();

        stage.act();
        batcher.begin();
        stage.draw();
        batcher.end();
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

    @Override
    public boolean keyDown(int keycode) {
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
