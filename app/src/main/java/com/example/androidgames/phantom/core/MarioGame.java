package com.example.androidgames.phantom.core;

import com.example.androidgames.framework.Screen;
import com.example.androidgames.framework.gfx.AndroidGame;
import com.example.androidgames.phantom.objects.base.Creature;
import com.example.androidgames.phantom.screens.SplashLoadingScreen;

public class MarioGame extends AndroidGame{
	boolean firstTimeCreate = true;
	public MarioResourceManager resourceManager;
	public MarioSoundManager soundManager;
	public static final int QUIT_GAME_DIALOG = 0;
	public static final int VERSION = 1;

	public Screen getStartScreen() {
		 if (firstTimeCreate) {
	            //Assets.load(this);
	            soundManager=new MarioSoundManager(this);
	            resourceManager=new MarioResourceManager(this);
	            firstTimeCreate = false;
	            Creature.WAKE_UP_VALUE_DOWN_RIGHT=WIDTH/16;
	            //Creature.WAKE_UP_VALUE_DOWN_RIGHT=HEIGHT/16;
	      }
         return new SplashLoadingScreen(this);
	}

	
	 @Override
	 public void onResume() {
	      super.onResume();
	      MarioSoundManager.playMusic();
	      
	 }

    @Override
    public void onPause() {
	    super.onPause();
	   	MarioSoundManager.pauseMusic();
    }
	
	@Override
	public void setScreenWithFade(Screen screen) {
		soundManager.playswitchScreen();
		super.setScreenWithFade(screen);
	}


	
}
