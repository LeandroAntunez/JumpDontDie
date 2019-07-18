package com.leandro.antunez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainClass extends ApplicationAdapter {

	private Texture minijoe;
	private Texture spike;
	private TextureRegion spikeRegion;
	private SpriteBatch batch;
	private int width, height;
    private float widthJoe, heightJoe;
	@Override
	public void create() {
		minijoe = new Texture("minijoe.png");
		spike = new Texture("spike.png");
		batch = new SpriteBatch();
		spikeRegion = new TextureRegion(spike, 0, 0, spike.getWidth(), spike.getHeight());
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		widthJoe = minijoe.getWidth();
		heightJoe = minijoe.getHeight();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(spikeRegion, 200, 0);
		batch.draw(minijoe, 0, 0);
		batch.draw(minijoe,0 , height - heightJoe);
		batch.draw(minijoe, width - widthJoe , 0);
		batch.draw(minijoe, width - widthJoe , height - heightJoe);
		batch.end();
	}

	@Override
	public void dispose() {
		minijoe.dispose();
		spike.dispose();
		batch.dispose();
	}
}
