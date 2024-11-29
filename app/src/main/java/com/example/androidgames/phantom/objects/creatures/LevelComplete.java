package com.example.androidgames.phantom.objects.creatures;

import android.graphics.Bitmap;

import com.example.androidgames.phantom.core.MarioResourceManager;
import com.example.androidgames.phantom.core.animation.Animation;
import com.example.androidgames.phantom.core.tile.TileMap;
import com.example.androidgames.phantom.objects.base.Creature;





public class LevelComplete extends Creature {
	
	private Animation level;

	
	public LevelComplete(int pixelX, int pixelY) {
		super(pixelX, pixelY);
		setIsItem(true);
		setIsAlwaysRelevant(true);
		Bitmap shroom = MarioResourceManager.levelComplete;
		level = new Animation();
		level.addFrame(shroom, 1000);
		level.addFrame(shroom, 1000);
		setAnimation(level);
	}
	
	@Override
	public void updateCreature(TileMap map, int time) {
	
	}
}

