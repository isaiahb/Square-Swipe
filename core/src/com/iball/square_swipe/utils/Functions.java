package com.iball.square_swipe.utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by isaiah on 2016-05-02.
 */
public class Functions {
    public static float random(float min, float max) {
        float random = (float)Math.random();
        float diff = max - min;
        float r = random * diff;
        return min + r;
    }

    //defualt 1920 x 1080
    public static int getFontSize(float fontSize) {
        float density = Gdx.graphics.getDensity();
        int size = (int)(fontSize * density);
        size = size <= 150 ? size : 150;
        return size;
    }
}
