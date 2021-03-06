package com.iball.square_swipe.renderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.iball.square_swipe.Colors;
import com.iball.square_swipe.utils.Functions;

import java.util.ArrayList;

/**
 * Created by isaiah on 2016-05-02.
 */
public class RectangleExplosion {
    public Vector2 position;
    ArrayList<Particle> particles;
    public float life;
    public float maxLife;
    public float transparency = 1;

    public RectangleExplosion(Rectangle rectangle, int amount, int size, float duration, Color startColor, Color endColor) {
        particles = new ArrayList<Particle>();
        for (int i = 0; i < amount; i++) {
            Particle p = new Particle(rectangle.randomVector(), new Vector2(size, size));
            particles.add(p);
            p.color = Colors.offsetColor(startColor, 0.05f);
            p.deadColor = Colors.offsetColor(endColor, 0.05f);
            p.deadFadeTime = duration/2f;
            p.alive = false;
            Vector2 velo = new Vector2(p.position.x - rectangle.position.x, p.position.y - rectangle.position.y);
            velo.nor();
            velo.scl(Functions.random(0.1f, Gdx.graphics.getWidth() / 7.5f));
            p.velocity = velo;
            maxLife = duration;
        }
    }

    public void update(float delta) {
        life += delta;
        if (life > maxLife/2f) {
            transparency = 1 - (life - maxLife/2);
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update(delta);
            particles.get(i).transparency = transparency;
        }
    }

    public void render(ShapeRenderer shapeRenderer, boolean started) {
        for (int i = 0;i < particles.size(); i++) {
            particles.get(i).render(shapeRenderer, started);
        }
    }
}
