package com.iball.square_swipe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.iball.square_swipe.Colors;
import com.iball.square_swipe.Main;
import com.iball.square_swipe.renderables.enums.YAlignment;
import com.iball.square_swipe.utils.Functions;
import com.iball.square_swipe.renderables.Rectangle;
import com.iball.square_swipe.Swipe;
import com.iball.square_swipe.renderables.RectangleExplosion;
import com.iball.square_swipe.renderables.Text;

import java.util.ArrayList;


/**
 * Created by isaiah on 2016-04-27.
 */


public class GameManager implements InputProcessor {
    Main main;
    Vector2 touchedDown;
    Vector2 mid;
    Vector2 position;
    int gridSize = 6;
    float offset; //calculate based on the grid size
    float padding = 0.1f;
    float paddingPixels;

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    public ArrayList<Rectangle> shots = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> shotsToRemove = new ArrayList<Rectangle>();

    public ArrayList<RectangleExplosion> explosions = new ArrayList<RectangleExplosion>();
    public ArrayList<RectangleExplosion> explosionsToRemove = new ArrayList<RectangleExplosion>();

    public Rectangle rectangle;
    public Rectangle background;
    public Rectangle gameArea;

    float shotTime = 2;
    float shotTimer = 0.3f;
    boolean first = true;
    boolean started = false;
    Color backgroundColor = Colors.Clouds;//DarkPurple;

    int lifes = 0;
    int shotsFired  = 0;

    Text title;
    Text scoreText;
    Text highscoreText;
    Text hintText;

    void start() {
        started = true;
        rectangle.reset(); background.reset(); gameArea.reset();
        shotsFired = 0;
    }
    public void endGame() {
        started = false;
        rectangle.alive = false; background.alive = false; gameArea.alive = false;
        rectangle.wasDead = true; background.wasDead = true; gameArea.wasDead = true;
        first = false;
        int highscore = main.preferences.getInteger("highscore",  0);
        int score = shotsFired;
        if (score > highscore) {
            main.preferences.putInteger("highscore", score);
            highscore = score;
            main.preferences.flush();
        }

        //Todo save highscore
        //Todo display highscore and score
        //Todo display rate game and other apps buttons
    }

    GameManager(Main main) {
        this.main = main;
        Gdx.input.setInputProcessor(this);
        mid = new Vector2(width/2, height/2);
        paddingPixels = (padding * width);

        int gameSize = (int)(width * (1 - 2 * padding));
        offset = gameSize / (float)gridSize;
        position = new Vector2();

        title = new Text(this.main.font50, Main.Title, width/2, height - height/(height/50));
        scoreText = new Text(this.main.font16, "Shots Fired " + 0, offset, title.position.y);
        highscoreText = new Text(this.main.font16, "Highscore " + 0, width - offset, scoreText.position.y);

        scoreText.setYAlignment(YAlignment.Top);
        highscoreText.setYAlignment(YAlignment.Top);


        rectangle = new Rectangle(0, 0, offset, offset);
        rectangle.color = Colors.Clouds;
        rectangle.deadColor = Colors.WetAsphalt;
        rectangle.orginOffset = new Vector2(offset/2, offset/2);
        rectangle.deadFadeTime = 0.4f;

        background = new Rectangle(0, 0, width, height);
        background.color = Colors.DarkPurple;
        background.deadColor = Colors.WetAsphalt;
        background.deadFadeTime = 0.4f;

        gameArea = new Rectangle(paddingPixels - offset/2f, height/2f - offset*(gridSize + 1)/2f, offset*(gridSize + 1), offset*(gridSize + 1));
        gameArea.color = Colors.Purple;
        gameArea.deadColor = Colors.MidnightBlue;
        gameArea.deadFadeTime = 0.4f;

    }
    void print(String title, Vector2 vector) {
        System.out.println(title + " x: " + vector.x + ", y: " + vector.y);
    }

    //we will call this function when the user swipes the screen to help us determine what direction they swiped
    public Swipe swiped(Vector2 start, Vector2 end) {
        Vector2 delta = end.sub(start);
        if(Math.abs(delta.y) > Math.abs(delta.x)) {
            //we are either swiping up or down
            if(delta.y > 0) {
                return Swipe.Down;
            } else {
                return Swipe.Up;
            }

        } else {
            //we are swiping left or right
            //we are either swiping up or down
            if(delta.x > 0) {
                return Swipe.Right;
            } else {
                return Swipe.Left;
            }
        }


    }
    public void move(Vector2 touchedUp) {
        if (!started) {
            if (Colors.sameColors(rectangle.getCurrentColor(), rectangle.deadColor) || first) {
                start();

            }
            return;
        }

        Swipe swipe = swiped(touchedDown, touchedUp);
        position.add(swipe.direction);
        position.x = position.x > gridSize/2 ? gridSize/2 : position.x;
        position.x = position.x < -gridSize/2 ? -gridSize/2 : position.x;

        position.y = position.y > gridSize/2 ? gridSize/2 : position.y;
        position.y = position.y < -gridSize/2 ? -gridSize/2 : position.y;
    }

    //a function to generate a random float between the two values a and b.

    //returns the pixel position of a random spot somewhere in the game area
    Vector2 randomGridSpot() {
        float x = Functions.random(-gridSize/2, gridSize/2);
        float y = Functions.random(-gridSize/2, gridSize/2);
        return new Vector2(mid.x + x * offset, mid.y + y * offset);
    }

    public void createShot(float speedMultiplier) {
        shotsFired ++;
        System.out.println("shots fired "+shotsFired);
        int size = (int)(offset / 2f);
        Rectangle shot = new Rectangle(0, 0, size, size);
        shot.orginOffset.set(size/2f, size/2f);
        shots.add(shot);

        int x = (int)Functions.random(0, 1.99f);
        int y = (int)Functions.random(25, height - 25);
        float speed = width/1.2f;

        x *= (width);
        x = x == 0 ? x - 25 : x + 25;

        shot.position.set(x,y);
        Vector2 direction = randomGridSpot().sub(shot.position);
        direction.nor();
        shot.velocity.set(direction.scl(speed));
    }

    public void updateRectangle(float delta) {
        Vector2 p = new Vector2();
        p.x = mid.x + position.x * offset;// - offset/2;
        p.y = mid.y + position.y * offset;// - offset/2;
        rectangle.position = p;

        for (int i = 0; i < shots.size(); i++) {
            if (rectangle.isCollide(shots.get(i))) {
                shotsToRemove.add(shots.get(i));
                explosions.add(new RectangleExplosion(shots.get(i), 30, (int)(offset/8f), 2f, Colors.MidnightBlue, Colors.DarkPurple));
                lifes--;
                if (lifes < 0) endGame();
            }
        }
    }
public void updateExplosions(float delta) {
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update(delta);
            if (explosions.get(i).life >= explosions.get(i).maxLife) {
                explosionsToRemove.add(explosions.get(i));
            }
        }
        for(int i = 0; i < explosionsToRemove.size(); i++){
            explosions.remove(explosionsToRemove.get(i));
        }
        explosionsToRemove.clear();
    }
    public void updateShots(float delta) {
        for (int i = 0; i < shots.size(); i++) {
            shots.get(i).update(delta);
            if (shots.get(i).life >= 5) {
                shotsToRemove.add(shots.get(i));
            }
        }
        for(int i = 0; i < shotsToRemove.size(); i++){
            //explosions.add(new RectangleExplosion(shotsToRemove.get(i), 20, 20, 2));
            shots.remove(shotsToRemove.get(i));
        }
        shotsToRemove.clear();


        if (started)
            shotTimer -= delta;

        if (shotTimer <= 0) {
            shotTimer += shotTime;
            createShot(1);
            if (shotsFired > 10)
                createShot(1);
        }
    }

    public void update(float delta) {
        updateRectangle(delta);
        updateShots(delta);updateExplosions(delta);
        rectangle.update(delta);
        background.update(delta);
        gameArea.update(delta);
    }

    public void renderSquare(ShapeRenderer shapeRenderer) {
        rectangle.render(shapeRenderer, true);
    }
    public void renderGameArea(ShapeRenderer shapeRenderer) {
        gameArea.render(shapeRenderer, true);
    }
    public void renderShots(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < shots.size(); i++) {
            shots.get(i).render(shapeRenderer, true);
        }
    }
    public void renderExplosions(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).render(shapeRenderer, true);
        }
    }
    public void renderText() {
        main.batch.begin();
        float xOffset = Gdx.graphics.getPpiX()/5f;
        float yOffset = height - Gdx.graphics.getPpiY()/5f;
//        main.font16.draw(main.batch, "shots fired " + shotsFired, xOffset, yOffset);
//        main.font16.draw(main.batch, "highscore " + main.preferences.getInteger("highscore",  0), xOffset, yOffset - main.font16.getLineHeight());

        title.render(main.batch, true);
        //highscoreText.setText("Highscore " + main.preferences.getInteger("highscore", 0));
        highscoreText.render(main.batch, true);

        //scoreText.setText("Shots Fired " + shotsFired);
        scoreText.render(main.batch, true);
        main.batch.end();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //main.shapeRenderer.setColor(backgroundColor);
        background.render(main.shapeRenderer, true);
        renderGameArea(main.shapeRenderer);
        renderSquare(main.shapeRenderer);
        renderShots(main.shapeRenderer);
        renderExplosions(main.shapeRenderer);
        main.shapeRenderer.end();
        renderText();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchedDown = new Vector2(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        move(new Vector2(screenX, screenY));
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
