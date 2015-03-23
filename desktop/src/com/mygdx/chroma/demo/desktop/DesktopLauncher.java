package com.mygdx.chroma.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.chroma.demo.ChromaDemoMain;
import com.mygdx.chroma.demo.Constants;

/**
 * Launches the desktop version of the demo for Chroma.
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ChromaDemoMain(), config);
		config.title = "Chroma";
        config.width = Constants.WIDTH;
        config.height = Constants.HEIGHT;
	}
}
