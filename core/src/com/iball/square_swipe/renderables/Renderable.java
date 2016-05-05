package com.iball.square_swipe.renderables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.iball.square_swipe.Colors;

public abstract class Renderable {
    public Color color = Colors.MidnightBlue;
    public Color deadColor = Colors.Purple;
    public float transparency = 1;

    public Vector2 position;
    public Vector2 size;
    public Vector2 velocity;
    public Vector2 orginOffset;
    public float life = 0;
    public float dead = 0;
    public float deadFadeTime = 2;
    public boolean alive = true;
    public boolean wasDead = false;

    public void update(float delta) {
        if (alive) life += delta; else dead += delta;
        position.x = position.x + velocity.x * delta;
        position.y = position.y + velocity.y * delta;
    }

    public void reset() {
        life = 0;
        dead = 0;
        alive = true;
    }

    public Color getCurrentColor() {
        if (alive) {
            if (wasDead) {
                if (life < deadFadeTime)
                    return Colors.interpolate(deadColor, color, deadFadeTime, life);
                else
                    return color;
            }
            return (color);
        }
        else {
            if (dead < deadFadeTime)
                return Colors.interpolate(color, deadColor, deadFadeTime, dead);
            else
                return deadColor;
        }
    }
    public Color getTransparentColor() {
        Color c = getCurrentColor();
        c.a = transparency;
        return c;
    }

    public abstract void render(Object o, boolean started);

}
