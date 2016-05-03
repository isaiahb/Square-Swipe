package com.iball.square_swipe;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by isaiah on 2016-04-27.
 */
public enum Swipe {
    Left(-1, 0),
    Right(1, 0),
    Up(0, 1),
    Down(0, -1);

    public Vector2 direction;
    Swipe(int x, int y) {
        direction = new Vector2(x, y);
    }
};

