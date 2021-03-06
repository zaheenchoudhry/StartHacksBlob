package com.starthacks.blob;

import com.badlogic.gdx.Game;

public class MainActivity extends Game {

	private GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/

	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
	}
}
