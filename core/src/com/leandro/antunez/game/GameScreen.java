package com.leandro.antunez.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.leandro.antunez.game.entities.FloorEntity;
import com.leandro.antunez.game.entities.PlayerEntity;
import com.leandro.antunez.game.entities.SpikeEntity;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private Stage stage;
    private World world;
    private PlayerEntity playerEntity;
    private List<FloorEntity> floorEntityList = new ArrayList<FloorEntity>();
    private List<SpikeEntity> spikeEntityList = new ArrayList<SpikeEntity>();

    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, -10), true);

        world.setContactListener(new ContactListener() {

            private boolean areCollided(Contact contact, Object userA, Object userB){
                return (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB)) ||
                        (contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (areCollided(contact, "player", "floor")){
                    playerEntity.setJumping(false);
                    if (Gdx.input.isTouched()){
                        playerEntity.setMustJump(true);
                    }
                }

                if (areCollided(contact, "player", "spike")){
                    playerEntity.setAlive(false);
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void show() {
        Texture playerTexture = game.getAssetManager().get("minijoe.png");
        Texture spikeTexture = game.getAssetManager().get("spike.png");
        Texture floorTexture = game.getAssetManager().get("floor.png");
        Texture overfloorTexture = game.getAssetManager().get("overfloor.png");
        Vector2 position = new Vector2(1.5f, 1.5f);
        playerEntity = new PlayerEntity(world, playerTexture, position);


        floorEntityList.add(new FloorEntity(world, floorTexture, overfloorTexture, 0, 1000, 1));
        spikeEntityList.add(new SpikeEntity(world, spikeTexture, 6, 1));


        stage.addActor(playerEntity);
        for (FloorEntity floor : floorEntityList){
            stage.addActor(floor);
        }
        for (SpikeEntity spike : spikeEntityList){
            stage.addActor(spike);
        }

    }

    @Override
    public void hide() {
        playerEntity.detach();
        playerEntity.remove();
        for (FloorEntity floor : floorEntityList){
            floor.detach();
            floor.remove();
        }
        for (SpikeEntity spike : spikeEntityList){
            spike.detach();
            spike.remove();
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
