package com.iball.square_swipe.renderables;


import com.badlogic.gdx.math.Vector2;

/**
 * Created by isaiah on 2016-05-02.
 */
public class Particle extends Rectangle {
    public Particle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Particle(Vector2 position, Vector2 size) {
        super(position, size);
    }
}
