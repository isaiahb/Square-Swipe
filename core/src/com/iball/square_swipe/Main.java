package com.iball.square_swipe;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.iball.square_swipe.screens.GameScreen;
import com.iball.square_swipe.screens.SplashScreen;
import com.iball.square_swipe.services.LeadBolt;
import com.iball.square_swipe.utils.Functions;

public class Main extends Game {
    public static int Width = (int)(1080 * 0.4);
    public static int Height = (int)(1920 * 0.4);
    public static String Title = "Square Swipe";

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
	public Screen currentScreen;
    public BitmapFont font16, font20, font50;
    public Preferences preferences;
    public LeadBolt leadBolt;
    public static final String GooglePlayLink = "https://play.google.com/store/apps/developer?id=iball";


    public Main(LeadBolt leadBolt) {
        this.leadBolt = leadBolt;
    }
	@Override
	public void create () {
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // freetype font
        FreeTypeFontGenerator openSans = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(16 * Gdx.graphics.getDensity());
        font16 = openSans.generateFont(parameter);

        parameter.size = Functions.getFontSize(50);
        System.out.println("size density " + parameter.size);

        font50 = openSans.generateFont(parameter);

        parameter.size = (int)(20 * Gdx.graphics.getDensity());
        font20 = openSans.generateFont(parameter);

        preferences = Gdx.app.getPreferences(Title + " Preferences");

        currentScreen = new GameScreen(this);
        setScreen(currentScreen);
	}

	@Override
	public void render () {
        super.render();
	}
}
