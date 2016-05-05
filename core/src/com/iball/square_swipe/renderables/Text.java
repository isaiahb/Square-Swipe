package com.iball.square_swipe.renderables;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.iball.square_swipe.Colors;
import com.iball.square_swipe.renderables.enums.XAlignment;
import com.iball.square_swipe.renderables.enums.YAlignment;

/**
 * Created by isaiah on 2016-05-02.
 */
public class Text extends Renderable{
    BitmapFont font;
    String text;
    XAlignment xAlignment = XAlignment.Center;
    YAlignment yAlignment = YAlignment.Top;
    public GlyphLayout layout;


    public Text (BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.position = new Vector2(x, y);
        this.color = Colors.Clouds;
        layout = new GlyphLayout(); //dont do this every frame! Store it as member
        setText(text);
    }
    public void resetLayout() {
        layout.setText(font, text);
    }
    public void setText(String text) {
        this.text = text;
        resetLayout();
    }
    public void setXAlignment(XAlignment xAlignment) {
     this.xAlignment = xAlignment;
    }
    public void setYAlignment(YAlignment yAlignment) {
        this.yAlignment = yAlignment;
    }
    public Vector2 getAlignmentPosition() {
        float x, y;
        x = position.x;
        y = position.y;

        switch (xAlignment) {
            case Left:
                break;
            case Center:
                x -= layout.width/2f;
                break;
            case Right:
                x -= layout.width;
                break;
        }

        switch (yAlignment) {
            case Top:
                y -= layout.height;
                break;

            case Center:
                y -= layout.height/2f;
                break;

            case Bottom:
                break;
        }
        return new Vector2(x, y);
    }

    @Override
    public void render(Object object, boolean started) {
        SpriteBatch batch = (SpriteBatch) object;
        if (!started)
            batch.begin();

        batch.setColor(getCurrentColor());
//        font.setColor(getCurrentColor());
        Vector2 p = getAlignmentPosition();
        font.draw(batch, text, p.x, p.y);
        if (!started)
            batch.end();
    }
}
