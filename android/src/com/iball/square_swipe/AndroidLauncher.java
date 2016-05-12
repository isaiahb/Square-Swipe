package com.iball.square_swipe;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.iball.square_swipe.Main;
import com.iball.square_swipe.services.LeadBolt;

public class AndroidLauncher extends AndroidApplication implements LeadBolt {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Main(), config);
	}

	@Override
	public void showFullscreenAd() {
		
	}

	@Override
	public void showVideoAd() {

	}
}
