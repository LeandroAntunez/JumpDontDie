package com.leandro.antunez.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leandro.antunez.game.actors.ActorJugador;

public class MainGameScreen extends BaseScreen {

    private Stage stage;
    private Texture texturaJugador;

    MainGameScreen(MainGame game) {
        super(game);
        texturaJugador = new Texture("minijoe.png");
    }

    @Override
    public void show() {
        stage = new Stage();
        ActorJugador actorJugador = new ActorJugador(texturaJugador);
        stage.addActor(actorJugador);
        actorJugador.setPosition(20,100);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
