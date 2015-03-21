package com.mygdx.chroma.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.chroma.demo.screen.FightScreen;
import com.mygdx.chroma.demo.screen.ScreenManager;

/**
 * The main class for the demo for Chroma. *
 */
public class ChromaDemoMain extends ApplicationAdapter {
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		ScreenManager.setScreen(new FightScreen());
	}

	@Override
	public void dispose() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().update();

		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().render(batch);
	}

	@Override
	public void resize(int width, int height) {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resize(width, height);
	}

	@Override
	public void pause() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
	}

	@Override
	public void resume() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();
	}
}
