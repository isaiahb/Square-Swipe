package com.iball.square_swipe.renderables;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.iball.square_swipe.Colors;

/**
 * Created by isaiah on 2016-05-02.
 */
public class Text extends Renderable{
    BitmapFont font;
    String text;

    Text (BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.position = new Vector2(x, y);
        this.color = Colors.Clouds;
    }

    @Override
    public void render(Object object, boolean started) {
        SpriteBatch batch = (SpriteBatch) object;
        if (!started)
            batch.begin();

        batch.setColor(getCurrentColor());
        font.setColor(getCurrentColor());
        font.draw(batch, text, position.x, position.y);
        if (!started)
            batch.end();
    }
}
