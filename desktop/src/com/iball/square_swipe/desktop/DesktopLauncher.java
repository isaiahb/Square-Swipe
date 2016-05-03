package com.iball.square_swipe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.iball.square_swipe.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Main.Width;
        config.height = Main.Height;
        config.title = Main.Title;

		new LwjglApplication(new Main(), config);
	}
}
