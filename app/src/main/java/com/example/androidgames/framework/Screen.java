package com.example.androidgames.framework;

public abstract class Screen {
	protected final Game game;

	public Screen(Game game) {
		this.game = game;
	}

	/**
	 * updates screen, The Game instance will call it once in every iteration of
	 * the main loop.
	 * 
	 * @param deltaTime takes into account how much time passed since the last time the method was called) 
	 * that we can use to create framerate independent movement (more on this later)
	 */
	public abstract void update(float deltaTime);

	/**
	 * present screen (renders)The Game instance will call it once in every
	 * iteration of the main loop.
	 * 
	 * @param deltaTime deltaTime takes into account how much time passed since the last time the method was called) 
	 * that we can use to create framerate independent movement (more on this later)
	 */
	public abstract void paint(float deltaTime);

	/**
	 * THIS methods will be called when the game is paused o
	 */
	public abstract void pause();

	/**
	 * THIS methods will be called when the game is resumed
	 */
	public abstract void resume();

	/**
	 * method will be called by the Game instance in case Game.setScreen() is
	 * called. The Game instance will dispose of the current Screen via this
	 * method and thereby give the Screen an opportunity to release all its
	 * system resources (for example, graphical assets stored in Pixmaps) to
	 * make room for the new screen�s resources in memory. The call to the
	 * Screen.dispose() method is also the last opportunity for a screen to make
	 * sure that any information that needs persistence is saved.
	 */
	public abstract void dispose();
	
	
	/**
	 * Action performed on pressing back key when this screen is active (set as Current Screen of game)
	 */
	 public abstract void onBackPressed();
}
