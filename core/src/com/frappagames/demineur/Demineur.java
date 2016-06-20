package com.frappagames.demineur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.frappagames.demineur.Screens.MenuScreen;
import com.frappagames.demineur.Tools.Settings;

public class Demineur extends Game {
	public static final String TITLE  = "Le Démineur";
	public static       int    width  = 720;
	public static       int    height = 1280;
	public        SpriteBatch  batch;
	public static TextureAtlas atlas;

	@Override
	public void create() {
		atlas = new TextureAtlas(Gdx.files.internal("Demineur.pack"));
		batch = new SpriteBatch();
		Settings.load();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		atlas.dispose();
	}
}
