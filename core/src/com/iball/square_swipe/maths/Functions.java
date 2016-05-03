package com.iball.square_swipe.maths;

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
}
