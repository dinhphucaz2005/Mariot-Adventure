package com.example.androidgames.phantom.objects.tiles;

import com.example.androidgames.phantom.core.MarioResourceManager;
import com.example.androidgames.phantom.core.animation.Animation;
import com.example.androidgames.phantom.core.tile.GameTile;


public class FireTile extends GameTile {

	private Animation active;
	
	public FireTile(int pixelX, int pixelY) {
		super(pixelX, pixelY, null, null);
		setIsSloped(false);
		active = new Animation(400).addFrame(MarioResourceManager.Fire_Tile[0]).addFrame(MarioResourceManager.Fire_Tile[1]);
		setAnimation(active);
	}
	
	public void update(int time) {
		super.update(time);
	}
	
}