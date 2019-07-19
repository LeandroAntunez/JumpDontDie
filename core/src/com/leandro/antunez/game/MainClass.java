package com.leandro.antunez.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MainClass extends ApplicationAdapter {

    @Override
    public void create() {
        Procesador procesador = new Procesador();
        Gdx.input.setInputProcessor(procesador);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.justTouched()){
            System.out.println("Estas tocando la pantalla.");
        }
    }

    @Override
    public void dispose() {
    }
}
