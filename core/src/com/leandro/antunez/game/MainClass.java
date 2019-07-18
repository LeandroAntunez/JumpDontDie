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
	}

	@Override
	public void dispose() {
		minijoe.dispose();
		batch.dispose();
	}
}
