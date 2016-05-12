package com.iball.square_swipe;

import android.os.Bundle;

import com.apptracker.android.track.AppTracker;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.iball.square_swipe.Main;
import com.iball.square_swipe.services.LeadBolt;

public class AndroidLauncher extends AndroidApplication implements LeadBolt {

	// Leadbolt SDK configurations
	private static final String APP_API_KEY 		    = "is2byYEVjbXiFjVjaYIt6sM4aEIqMWZ3"; // change this to your App specific API KEY
	private static final String LOCATION_CODE		    = "inapp";
	private static final String LOCATION_CODE_VIDEO 	= "video";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Main(), config);

		if(savedInstanceState == null) {
			// Initialize LeadBolt SDK with your api key
			AppTracker.startSession(getApplicationContext(),APP_API_KEY,AppTracker.ENABLE_AUTO_CACHE);
		}
		// cache LeadBolt Ad without showing it
		AppTracker.loadModuleToCache(getApplicationContext(),LOCATION_CODE);

	}

	@Override
	public void showFullscreenAd() {
		if(AppTracker.isAdReady(LOCATION_CODE)&&AppTracker.isInternetAvailable(this)) {
			AppTracker.loadModule(getApplicationContext(),LOCATION_CODE);
		}
	}

	@Override
	public void showVideoAd() {

	}
}
