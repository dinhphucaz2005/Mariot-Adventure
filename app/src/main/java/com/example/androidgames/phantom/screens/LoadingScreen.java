package com.example.androidgames.phantom.screens;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint.Align;

import com.example.androidgames.framework.Game;
import com.example.androidgames.framework.Graphics;
import com.example.androidgames.framework.Screen;
import com.example.androidgames.framework.gfx.AndroidGame;
import com.example.androidgames.framework.gfx.AndroidGraphics;
import com.example.androidgames.phantom.core.MarioGame;
import com.example.androidgames.phantom.core.MarioResourceManager;
import com.example.androidgames.phantom.core.Settings;
import com.example.androidgames.phantom.util.SpriteMap;

public class LoadingScreen extends Screen {
	private boolean drawnOnce=false;
	private Bitmap[] font;
  
	public LoadingScreen(Game game) {
         super(game);
	     font=new SpriteMap(MarioResourceManager.loadImage("items/font_white_8.png"),96,1).getSprites();
    }

    @Override
    public void update(float deltaTime) {
    	if (!drawnOnce) return;
        ((MarioGame)game).soundManager.loadResouces();
        ((MarioGame)game).resourceManager.loadResouces();
        ((AndroidGame) game).setScreenWithFade(new GuiMenuScreen(game));//mainMenuScreen);
        Settings.loadPreferences((Context) game);
     }

    @Override
    public void paint(float deltaTime) {
    	
        Graphics g = game.getGraphics();
        //g.drawARGB(200, , g, b)
        Paint paint=new Paint(color.holo_blue_bright);
        paint.setTextAlign(Align.CENTER);
        paint.setTextSize(18);
        paint.setAntiAlias(true);
        //g.drawString("Loading....", 100,200,paint);//game.getScreenWidth()/2,game.getScreenHeight()/2, paint);
        ((AndroidGraphics) g).drawBitmapFont(font,"LOADING GAME....",game.getScreenWidth()/2-50,game.getScreenHeight()/2-6,8);
        //g.drawImage(Assets.splash,0, 0);
       	drawnOnce=true;
        
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void onBackPressed() {

    }
}