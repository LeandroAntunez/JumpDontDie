package com.leandro.antunez.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leandro.antunez.game.constants.Constants;

import java.awt.Polygon;

import static com.leandro.antunez.game.constants.Constants.PIXEL_IN_METERS;

public class FloorEntity extends Actor {

    private final World world;
    private final Texture floor;
    private final Texture overfloor;
    private final Body body;
    private final Fixture fixture;

    public FloorEntity(World world, Texture floor, Texture overfloor, float x, float width, float y){
        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        BodyDef def = new BodyDef();
        def.position.set(x + width / 2, y - 0.5f);
        this.body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, 0.5f);
        this.fixture = body.createFixture(box, 1);
        fixture.setUserData("floor");
        box.dispose();

        setSize(width * PIXEL_IN_METERS, PIXEL_IN_METERS);
        setPosition((x - width / 2) * PIXEL_IN_METERS, (y - 1) * PIXEL_IN_METERS);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(floor, getX(), getY(), getWidth(), getHeight());
        batch.draw(overfloor, getX(), getY() + 0.9f * getHeight(), getWidth(), 0.1f * getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
