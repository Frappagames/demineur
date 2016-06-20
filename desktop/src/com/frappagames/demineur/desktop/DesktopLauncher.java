package com.frappagames.demineur.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frappagames.demineur.Demineur;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Demineur.height / 2;
		config.width = Demineur.width / 2;
		config.title = Demineur.TITLE;
		new LwjglApplication(new Demineur(), config);
	}
}
