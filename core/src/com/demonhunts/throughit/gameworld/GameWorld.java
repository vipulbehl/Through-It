package com.demonhunts.throughit.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.demonhunts.throughit.gameobjects.ScrollHandler;
import com.demonhunts.throughit.gameobjects.Dot;

public class GameWorld {

    private Dot dot;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score;

    public GameWorld(int midPointY) {
        dot = new Dot(33,midPointY-9,17,17);
        scroller = new ScrollHandler(this,midPointY + 66);
        ground = new Rectangle(0,midPointY+66,136,11);
    }

    public void update(float delta){
        if(delta > .15f){
            delta = .15f;
        }

        dot.update(delta);
        scroller.update(delta);

        if (scroller.collides(dot)) {
            scroller.stop();
        }

        if(Intersector.overlaps(dot.getBoundingCircle(),ground)){
            scroller.stop();
        }
    }

    public int getScore(){
        return score;
    }

    public void addScore(int increment){
        score = score + increment;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public void setScroller(ScrollHandler scroller) {
        this.scroller = scroller;
    }

    public Dot getDot() {
        return dot;
    }
}
