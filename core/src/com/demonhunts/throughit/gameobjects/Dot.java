package com.demonhunts.throughit.gameobjects;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

public class Dot {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;
    private boolean direction;
    private boolean clickCheck;
    private int b ;
    private Circle boundingCircle;

    public Dot(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,460);
        direction = false;
        clickCheck=false;
        b=5;
        boundingCircle = new Circle();
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void setBoundingCircle(Circle boundingCircle) {
        this.boundingCircle = boundingCircle;
    }

    public void update(float delta) {
        //Main algorithm to control dot's movement
        if(clickCheck) {
            if (direction) {
                if(b==1)
                {
                    velocity.add(0,-50);
                    b=5;
                }
                velocity.add(0, -5);
            } else {
                if(b==1)
                {
                    velocity.add(0,50);
                    b=5;
                }
                velocity.add(0, 5);
            }
//        velocity.add(acceleration.cpy().scl(delta));
            if (velocity.y > 200) {
                velocity.y = 200;
            }
            position.add(velocity.cpy().scl(delta));

        }
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);
    }

    public void onClick() {
        clickCheck=true;
        b=1;
        if(direction)
            direction = false;
        else
            direction = true;
//        velocity.y = -140;
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
