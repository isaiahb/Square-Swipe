package com.iball.square_swipe.renderables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.iball.square_swipe.Colors;
import com.iball.square_swipe.maths.Functions;

/**
 * Created by isaiah on 2016-04-28.
 */
public class Rectangle extends Renderable{

    public Rectangle(float x, float y, float width, float height) {
        position = new Vector2(x, y);
        size = new Vector2(width, height);
        velocity = new Vector2();
        orginOffset = new Vector2();
    }
    public Rectangle(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
        velocity = new Vector2();
        orginOffset = new Vector2();
    }

    public Vector2 randomVector(){
        Vector2 random = new Vector2(Functions.random(getMin().x,getMax().x),Functions.random(getMin().y,getMax().y));
        return random;
    }


    public Vector2 getMin() {
        return new Vector2(position.x - orginOffset.x, position.y - orginOffset.y);
    }
    public Vector2 getMax() { return new Vector2(position.x - orginOffset.x + size.x, position.y - orginOffset.y + size.y);}

    public boolean isCollide(Rectangle rect) {
        if (rect.getMax().y < getMin().y) return false;
        if (rect.getMax().x < getMin().x) return false;
        if (rect.getMin().y > getMax().y) return false;
        if (rect.getMin().x > getMax().x) return false;
        return true;
    }

    public void render(Object object, boolean started) {
        ShapeRenderer shapeRenderer = (ShapeRenderer)object;
        if (!started)
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(getCurrentColor());
        shapeRenderer.rect(position.x - orginOffset.x, position.y - orginOffset.y, size.x, size.y);

        if (!started)
            shapeRenderer.end();
    }
}
