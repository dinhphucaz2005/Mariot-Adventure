package com.example.androidgames.framework.input;

import java.util.List;

import com.example.androidgames.framework.Input;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

public class AndroidInput implements Input {
	AccelerometerHandler accelHandler;
	KeyboardHandler keyHandler;
	TouchHandler touchHandler;

	public AndroidInput(Context context, View view, float scaleX, float scaleY) {
		accelHandler = new AccelerometerHandler(context);
		keyHandler = new KeyboardHandler(view);
		if (VERSION.SDK_INT < 5)
			touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
		else
			touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
	}

	public boolean isKeyPressed(int keyCode) {
		return keyHandler.isKeyPressed(keyCode);
	}

	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}

	public float getAccelX() {
		return accelHandler.getAccelX();
	}

	public float getAccelY() {
		return accelHandler.getAccelY();
	}

	public float getAccelZ() {
		return accelHandler.getAccelZ();
	}

	public List<TouchEvent> getTouchEvents() {
		return touchHandler.getTouchEvents();
	}

	public List<KeyEvent> getKeyEvents() {
		return keyHandler.getKeyEvents();
	}
	
	public void registerAccListener(){
		accelHandler.registerListener();
	}
	
	public void unRegisterAccListener(){
		accelHandler.unRegisterListener();
	}
	
	public boolean hasAccelerometer(){
		return accelHandler.isEnabled();
	}
}