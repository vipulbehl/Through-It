package com.demonhunts.throughit.gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

public class Dot {

    private Vector2 position;
    private Vector2 velocity;

    private int width;
    private int height;
    private boolean direction;
    private boolean clickCheck;
    private boolean sharpTurn;
    private boolean initClick;
    private Circle boundingCircle;
    private boolean isAlive;

    public Dot(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        direction = false;
        clickCheck=false;
        sharpTurn=false;
        initClick=true;
        boundingCircle = new Circle();
        isAlive = true;
    }

    public void update(float delta) {
        if(clickCheck && initClick){
            velocity.add(0,110);
            initClick=false;
        }
        if(clickCheck) {
            if (direction) {
                if(sharpTurn)
                {
                    velocity.add(0,-220);
                    sharpTurn=false;
                }
                Gdx.app.log("Upwards : ",""+velocity.toString());
            } else {
                if(sharpTurn)
                {
                    velocity.add(0,220);
                    sharpTurn=false;
                }
                Gdx.app.log("Downwards : ",""+velocity.toString());
            }
            position.add(velocity.cpy().scl(delta));

        }
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);
    }

    public void onClick() {
        clickCheck=true;
        sharpTurn=true;
        if(direction)
            direction = false;
        else
            direction = true;
    }

    public void onRestart(int y) {
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        isAlive = true;
        direction = false;
        clickCheck=false;
        sharpTurn=false;
        initClick=true;
    }

    public void die(){
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void setBoundingCircle(Circle boundingCircle) {
        this.boundingCircle = boundingCircle;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
