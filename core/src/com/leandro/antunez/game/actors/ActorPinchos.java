package com.leandro.antunez.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPinchos extends Actor {

    private TextureRegion pinchosRegion;

    public ActorPinchos(TextureRegion pinchosRegion){
        this.pinchosRegion = pinchosRegion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pinchosRegion, getX(), getY());
    }
}
