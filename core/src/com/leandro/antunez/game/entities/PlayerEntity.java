package com.leandro.antunez.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.leandro.antunez.game.constants.Constants.IMPULSE_JUMP;
import static com.leandro.antunez.game.constants.Constants.PIXEL_IN_METERS;

public class PlayerEntity extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean alive = true, jumping = false, mustJump = false;

    public PlayerEntity(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("player");
        box.dispose();
        setSize(PIXEL_IN_METERS, PIXEL_IN_METERS);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.justTouched() || isMustJump()){
            mustJump = false;
            jump();
        }

        if (alive) {
            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(8, speedY);
        }
        if (jumping){
            body.applyForceToCenter(0, -IMPULSE_JUMP * 1.115f, true);
        }
    }

    private void jump() {
        if (!jumping && isAlive()){
            jumping = true;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, IMPULSE_JUMP, position.x, position.y, true);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * PIXEL_IN_METERS,
                (body.getPosition().y - 0.5f) * PIXEL_IN_METERS);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public void setJumping(boolean isJumping) {
        this.jumping = isJumping;
    }

    public boolean isMustJump() {
        return mustJump;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
