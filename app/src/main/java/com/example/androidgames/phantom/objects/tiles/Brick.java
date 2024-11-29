package com.example.androidgames.phantom.objects.tiles;

import android.graphics.Bitmap;

import com.example.androidgames.phantom.core.MarioSoundManager;
import com.example.androidgames.phantom.core.Settings;
import com.example.androidgames.phantom.core.tile.GameTile;
import com.example.androidgames.phantom.core.tile.TileMap;
import com.example.androidgames.phantom.objects.creatures.Coin;
import com.example.androidgames.phantom.objects.creatures.Mushroom;
import com.example.androidgames.phantom.objects.creatures.Score;
import com.example.androidgames.phantom.objects.mario.Mario;
import com.example.androidgames.phantom.particles.ParticleSystem;



public class Brick extends GameTile {

	private MarioSoundManager soundManager;
	private TileMap map;
	
	private int numCoins;
	private boolean hasMushroom;
	
	public Brick(int pixelX, int pixelY, TileMap map, Bitmap img,MarioSoundManager soundManager,int numCoins,
			boolean hasMushroom) {
		// int pixelX, int pixelY, Animation anim, Image img
		super(pixelX, pixelY, null,img);
	
		setIsSloped(false);
		this.numCoins = numCoins;
		this.hasMushroom = hasMushroom;
		this.soundManager = soundManager;
		this.map = map;
	}
	
	@Override
	public void update(int time) {
		//super.update(time);
		if(getOffsetY() != 0) { setOffsetY(getOffsetY() + 2); }
	}
	
	@Override
	public void doAction() {

		if (numCoins > 0) {
			numCoins--;
			setOffsetY(-10);
			soundManager.playCoin();
			Coin newCoin = new Coin(getPixelX(), getPixelY());
			Score score = new Score(getPixelX(), getPixelY(),0);
			map.creaturesToAdd().add(newCoin);
			map.creaturesToAdd().add(score);
			Settings.addScore(100);
			Settings.addCoins(1);
			newCoin.shoot();
		} else if (hasMushroom) {
			setOffsetY(-10);
			soundManager.playItemSprout();
			Mushroom shroom = new Mushroom(getPixelX(), getPixelY() - 26,((Mario)map.getPlayer()).isFireMan());
			map.creaturesToAdd().add(shroom);
			Settings.addScore(100);
		} else {
			if (((Mario)map.getPlayer()).isSmall()){
				setOffsetY(-10);
				soundManager.playKick();
			}else{
				soundManager.playBrickShatter();
				map.particleSystem = new ParticleSystem(getPixelX(), getPixelY(), 6);
				map.getTiles()[getPixelX() >> 4][getPixelY() >> 4] = null;
				Settings.addScore(100);
			}
		}

	}
}