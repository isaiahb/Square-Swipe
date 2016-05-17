package com.iball.square_swipe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.iball.square_swipe.Main;
import com.iball.square_swipe.services.LeadBolt;
import com.iball.square_swipe.services.LeadBoltAdapter;

public class DesktopLauncher implements LeadBolt {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Main.Width;
        config.height = Main.Height;
        config.title = Main.Title;
        LeadBoltAdapter leadBolt = new LeadBoltAdapter();

		new LwjglApplication(new Main(leadBolt), config);
	}

	@Override
	public void showFullscreenAd() {
	// this would display a fullscreen ad on android.
	}

	@Override
	public void showVideoAd() {
	// this would display a video advertisement on android.
	}
}
