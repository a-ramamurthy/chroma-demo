package com.mygdx.chroma.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The main class for the demo for Chroma. *
 */
public class ChromaDemoMain extends ApplicationAdapter {
	private static final String TAG = ChromaDemoMain.class.getName();
	AssetManager am = new AssetManager();
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font=new BitmapFont();
		font.setColor(Color.WHITE);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Hello World", 200, 200);
		batch.end();
	}
	
	public void resize(int width, int height)
	{
		
	}
	
	public void pause()
	{
		
	
	}
	
	public void resume()
	{
		
	}
	
	public void dispose()
	{
		font.dispose();
		batch.dispose();
	}
}
