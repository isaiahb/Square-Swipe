package com.iball.square_swipe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.iball.square_swipe.Main;

/**
 * Created by isaiah on 2016-05-06.
 */
public class SplashScreen implements Screen {
    float time = 5;
    Main main;

    public SplashScreen(Main main) {
        this.main = main;

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        time -= delta;
        if (time <= 0) {
            main.currentScreen = this;
            main.setScreen(new GameScreen(main));
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
