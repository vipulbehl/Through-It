package com.demonhunts.throughit.gameworld;

import com.demonhunts.throughit.gameobjects.ScrollHandler;
import com.demonhunts.throughit.gameobjects.Dot;

public class GameWorld {

    private Dot dot;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        dot = new Dot(33,midPointY-9,17,17);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta){
        dot.update(delta);
        scroller.update(delta);
        if (scroller.collides(dot)) {
            scroller.stop();
        }
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
