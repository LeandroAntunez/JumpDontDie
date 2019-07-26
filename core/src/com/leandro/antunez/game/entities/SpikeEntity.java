package com.leandro.antunez.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.leandro.antunez.game.constants.Constants.PIXEL_IN_METERS;

public class SpikeEntity extends Actor {

    private final World world;
    private final Texture texture;
    private final Body body;
    private final Fixture fixture;

    public SpikeEntity(World world, Texture texture, float x, float y){
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(x, y + 0.5f);
        this.body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        box.set(vertices);
        this.fixture = body.createFixture(box, 1);
        box.dispose();


        setSize(PIXEL_IN_METERS, PIXEL_IN_METERS);
        setPosition((x - 0.5f) * PIXEL_IN_METERS, y * PIXEL_IN_METERS);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
