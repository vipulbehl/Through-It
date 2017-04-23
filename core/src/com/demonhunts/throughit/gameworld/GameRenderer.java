package com.demonhunts.throughit.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.demonhunts.throughit.ThroughIt;
import com.demonhunts.throughit.gameobjects.Dot;
import com.demonhunts.throughit.helpers.AssetLoader;
import com.demonhunts.throughit.gameobjects.ScrollHandler;
import com.demonhunts.throughit.gameobjects.Pipe;
import com.demonhunts.throughit.gameobjects.Grass;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.demonhunts.throughit.helpers.FontHelper;
import com.demonhunts.throughit.screens.EndScreen;
import com.demonhunts.throughit.screens.GameScreen;


public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private final ThroughIt game;

    private Dot dot;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    private TextureRegion bg, grass;
    private TextureRegion bar;
    long currentTime;

    private int midPointY;
    private int gameHeight;

    private FontHelper fontHelper;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY, final ThroughIt game){
        myWorld = world;
        this.game = game;
        this.midPointY = midPointY;
        this.gameHeight = gameHeight;

        cam = new OrthographicCamera();
        cam.setToOrtho(true,136,215);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        currentTime = TimeUtils.millis();
        fontHelper = new FontHelper();
        bg = AssetLoader.bg;
        initGameObjects();
        initAssets();
    }

    public void render(){
        dot = myWorld.getDot();
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);
        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(bg, 0, 0, bg.getRegionWidth(), bg.getRegionHeight());
        batcher.enableBlending();
        batcher.draw(AssetLoader.dot,dot.getX(),dot.getY(),dot.getWidth(),dot.getHeight());

        drawGrass();
        drawPipes();
        batcher.enableBlending();



        if (myWorld.isReady()) {
            AssetLoader.font.draw(batcher, "Touch me", fontHelper.centerAlign("Touch me"), 75);
        } else {

            if (myWorld.isGameOver()) {
                AssetLoader.font.draw(batcher, "Game Over", fontHelper.centerAlign("Game Over"), 85);

                if(TimeUtils.millis() - currentTime > 2000)
                    game.setScreen(new EndScreen(game,myWorld.getScore()));

            }

            String score = myWorld.getScore() + "";

            if(game.isPlayServices)
                game.playServices.unlockAchievement(myWorld.getScore());

            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2) - (3 * score.length() - 1), 11);
        }

        batcher.end();

        if(!myWorld.isGameOver())
            currentTime = TimeUtils.millis();
    }
    private void initGameObjects() {
        dot = myWorld.getDot();
        scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        bar = AssetLoader.bar;
    }

    private void drawGrass() {
        batcher.draw(grass, frontGrass.getX(), gameHeight-11,
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), gameHeight-11,
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawPipes() {
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY +   (pipe1.getHeight() +45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + (pipe2.getHeight()+45 ));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY +  (pipe3.getHeight() +45));
    }
}
