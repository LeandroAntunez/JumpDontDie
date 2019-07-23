package com.leandro.antunez.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorJugador extends Actor {

    private Texture texturaJugador;
    private boolean alive;

    public ActorJugador(Texture textureJugador){
        this.texturaJugador = textureJugador;
        setSize(textureJugador.getWidth(), textureJugador.getHeight());
        this.setAlive(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturaJugador, getX(), getY());
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
