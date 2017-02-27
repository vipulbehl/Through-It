package com.demonhunts.throughit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.demonhunts.throughit.gameworld.GameRenderer;
import com.demonhunts.throughit.gameworld.GameWorld;
import com.demonhunts.throughit.helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight/(screenWidth/gameWidth);
        int midPoint = (int) gameHeight/2;

        world = new GameWorld(midPoint);
        renderer = new GameRenderer(world);

        Gdx.input.setInputProcessor(new InputHandler(world.getDot()));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
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
