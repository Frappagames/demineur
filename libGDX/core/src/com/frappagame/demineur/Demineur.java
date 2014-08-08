package com.frappagame.demineur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.frappagame.demineur.Screens.GameScreen;
import com.frappagame.demineur.Screens.SplashScreen;

public class Demineur extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Démineur";
	public static final boolean USE_GL30 = false;
	public static final String VERSION = "0.0.0 Pre-alpha";
	
	@Override
	public void create () {
		setScreen(new SplashScreen(this));
		setScreen(new GameScreen(this));
		Gdx.input.setInputProcessor(new InputHandler());
	}
	
	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void pause () {
		super.pause();
	}

	@Override
	public void resume () {
		super.resume();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
	
}
