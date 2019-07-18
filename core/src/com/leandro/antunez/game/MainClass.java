package com.leandro.antunez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends ApplicationAdapter {

	private Texture minijoe;
	private SpriteBatch batch;

	@Override
	public void create() {
		minijoe = new Texture("minijoe.png");
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(minijoe, 0 , 0);
		batch.end();
	}

	@Override
	public void dispose() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		minijoe.dispose();
		batch.dispose();
	}
}
