package com.leandro.antunez.game.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPinchos extends Actor {

    private TextureRegion pinchosRegion;

    public ActorPinchos(TextureRegion pinchosRegion){
        this.pinchosRegion = pinchosRegion;
        setSize(pinchosRegion.getRegionWidth(), pinchosRegion.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pinchosRegion, getX(), getY());
    }

    @Override
    public void act(float delta) {
        setX(getX() - 250 * delta);
    }
}
