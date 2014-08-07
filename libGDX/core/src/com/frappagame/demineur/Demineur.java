package com.frappagame.demineur;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Demineur extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH + 128;
	public static final String TITLE = "Démineur";
	public static final boolean USE_GL30 = false;
	
	SpriteBatch batch;
	Texture img;
	TextureRegion tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile_blank, tile_explosed, tile_mine, tile_close, tile_flag, tile_question;
	Integer style;
	
	@Override
	public void create () {
		style = 0;
		batch = new SpriteBatch();
		img = new Texture("tiles.png");
		tile1 = new TextureRegion(img, 0, style, 128, 128);
		tile2 = new TextureRegion(img, 128, style, 128, 128);
		tile3 = new TextureRegion(img, 256, style, 128, 128);
		tile4 = new TextureRegion(img, 384, style, 128, 128);
		tile5 = new TextureRegion(img, 512, style, 128, 128);
		tile6 = new TextureRegion(img, 640, style, 128, 128);
		tile7 = new TextureRegion(img, 768, style, 128, 128);
		tile8 = new TextureRegion(img, 896, style, 128, 128);
		tile_blank = new TextureRegion(img, 1024, style, 128, 128);
		tile_explosed = new TextureRegion(img, 1152, style, 128, 128);
		tile_mine = new TextureRegion(img, 1280, style, 128, 128);
		tile_close = new TextureRegion(img, 1408, style, 128, 128);
		tile_flag = new TextureRegion(img, 1536, style, 128, 128);
		tile_question = new TextureRegion(img, 1664, style, 128, 128);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
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
}
