package com.example.androidgames.framework.sfx;

import java.io.IOException;

import com.example.androidgames.framework.Music;


import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;

public class AndroidMusic implements Music, OnCompletionListener, OnPreparedListener, OnSeekCompleteListener {
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;

	public AndroidMusic(AssetFileDescriptor assetDescriptor) {
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
					assetDescriptor.getStartOffset(),
					assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnCompletionListener(this);
	        mediaPlayer.setOnSeekCompleteListener(this);
	        mediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load music");
		}
	}

	@Override
	public void dispose() {
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
		// release all the resources it takes up
		mediaPlayer.release();
	}
    @Override
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}
    @Override
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}
    @Override
	public boolean isStopped() {
		return !isPrepared;
	}

	/**
	 * The pause() method simply checks whether the MediaPlayer instance is
	 * playing and calls its pause() method if it is.
	 */
	@Override
    public void pause() {
		if (mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}

	/**
	 * If we are already playing, we simply return from the function. Next we
	 * have a mighty try. . .catch block within which we check to see if the
	 * MediaPlayer is already prepared based on our flag; we prepare it if
	 * needed. If all goes well, we call the MediaPlayer.start() method, which
	 * will start the playback. This is conducted in a synchronized block, since
	 * we are using the isPrepared flag, which might get set on a separate
	 * thread because we are implementing the OnCompletionListener interface. In
	 * case something goes wrong, we throw an unchecked RuntimeException.
	 */
	@Override
	public void play() {
		if (mediaPlayer.isPlaying())
			return;
		try {
			synchronized (this) {
				if (!isPrepared)
					mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}
    @Override
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}

	/**
	 * The stop() method stops the MediaPlayer and sets the isPrepared flag in a
	 * synchronized block.
	 */
	@Override
    public void stop() {
		if (this.mediaPlayer.isPlaying() == true) {
			this.mediaPlayer.stop();
			synchronized (this) {
				isPrepared = false;
			}
		}
	}
    @Override
	public void onCompletion(MediaPlayer player) {
		synchronized (this) {
			isPrepared = false;
		}
	}
    
 

    @Override
    public void onPrepared(MediaPlayer player) {
        // TODO Auto-generated method stub
         synchronized (this) {
               isPrepared = true;
            }
        
    }

    @Override
    public void onSeekComplete(MediaPlayer player) {
        // TODO Auto-generated method stub
        
    }

}