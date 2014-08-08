package com.frappagame.demineur.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frappagame.demineur.Demineur;

public class GameScreen implements Screen {

	SpriteBatch batch;
	Texture img;
	Sprite tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile_blank, tile_explosed, tile_mine, tile_close, tile_flag, tile_question;
	Integer style;
	Demineur game;

	public GameScreen(Demineur demineur) {
		this.game = demineur;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(tile1, 0, 0);
		batch.draw(tile2, 128, 0);
		batch.draw(tile3, 256, 0);
		batch.draw(tile4, 384, 0);
		batch.draw(tile5, 512, 0);
		
		batch.draw(tile6, 0, 128);
		batch.draw(tile7, 128, 128);
		batch.draw(tile8, 256, 128);
		batch.draw(tile_blank, 384, 128);
		batch.draw(tile_explosed, 512, 128);
		
		batch.draw(tile_mine, 0, 256);
		batch.draw(tile_close, 128, 256);
		batch.draw(tile_flag, 256, 256);
		batch.draw(tile_question, 384, 256);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		style = 0;
		batch = new SpriteBatch();
		img = new Texture("tiles.png");
		tile1 = new Sprite(img, 0, style, 128, 128);
		tile2 = new Sprite(img, 128, style, 128, 128);
		tile3 = new Sprite(img, 256, style, 128, 128);
		tile4 = new Sprite(img, 384, style, 128, 128);
		tile5 = new Sprite(img, 512, style, 128, 128);
		tile6 = new Sprite(img, 640, style, 128, 128);
		tile7 = new Sprite(img, 768, style, 128, 128);
		tile8 = new Sprite(img, 896, style, 128, 128);
		tile_blank = new Sprite(img, 1024, style, 128, 128);
		tile_explosed = new Sprite(img, 1152, style, 128, 128);
		tile_mine = new Sprite(img, 1280, style, 128, 128);
		tile_close = new Sprite(img, 1408, style, 128, 128);
		tile_flag = new Sprite(img, 1536, style, 128, 128);
		tile_question = new Sprite(img, 1664, style, 128, 128);
	}

	@Override
	public void hide() {
		
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

}
