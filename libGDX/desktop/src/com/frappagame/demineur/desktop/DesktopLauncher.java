package com.frappagame.demineur.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frappagame.demineur.Demineur;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title   = Demineur.TITLE;
		config.width   = Demineur.WIDTH;
		config.height  = Demineur.HEIGHT;
		config.useGL30 = Demineur.USE_GL30;
		new LwjglApplication(new Demineur(), config);
	}
}
