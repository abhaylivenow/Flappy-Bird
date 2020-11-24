package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FlappyBirdGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = FlappyBirdGdxGame.HEIGHT;
		config.width = FlappyBirdGdxGame.WIDTH;
		config.title = FlappyBirdGdxGame.TITLE;
		new LwjglApplication(new FlappyBirdGdxGame(), config);
	}
}
