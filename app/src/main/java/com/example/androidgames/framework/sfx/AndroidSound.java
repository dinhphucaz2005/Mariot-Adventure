package com.example.androidgames.framework.sfx;

import com.example.androidgames.framework.Sound;

import android.media.SoundPool;

public class AndroidSound implements Sound {
	/** integer ID to keep track of various sounds, play them, and dispose them from memory*/
	int soundId;
	SoundPool soundPool;

	public AndroidSound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float volume) {
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}
	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}
}