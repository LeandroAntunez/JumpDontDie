package com.leandro.antunez.game.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leandro.antunez.game.BaseScreen;
import com.leandro.antunez.game.MainGame;

public class Scene2DScreen extends BaseScreen {

    private Stage stage;
    private Texture texturaJugador;
    private Texture texturaPinchos;
    private ActorJugador actorJugador;
    private ActorPinchos actorPinchos;
    private TextureRegion regionPinchos;

    Scene2DScreen(MainGame game) {
        super(game);
        texturaJugador = new Texture("minijoe.png");
        texturaPinchos = new Texture("spike.png");
        regionPinchos = new TextureRegion(texturaPinchos, 0, 0, texturaPinchos.getWidth(), texturaPinchos.getHeight());
        actorJugador = new ActorJugador(texturaJugador);
        actorPinchos = new ActorPinchos(regionPinchos);
    }

    @Override
    public void show() {
        stage = new Stage();
        stage.setDebugAll(true);
        stage.addActor(actorJugador);
        stage.addActor(actorPinchos);
        actorJugador.setPosition(20,100);
        actorPinchos.setPosition(400, 100);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        comprobarColisiones();
        stage.draw();
    }

    private void comprobarColisiones() {
        if (actorJugador.isAlive() && actorJugador.getX() + actorJugador.getWidth() > actorPinchos.getX()){
            System.out.println("Hay colision");
            actorJugador.setAlive(false);
        }
    }


}
