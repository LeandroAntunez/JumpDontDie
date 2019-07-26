package com.leandro.antunez.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.leandro.antunez.game.entities.PlayerEntity;

public class GameScreen extends BaseScreen {

    private Stage stage;
    private World world;
    private PlayerEntity playerEntity;

    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, -10), true);
    }

    @Override
    public void show() {
        Vector2 position = new Vector2(1, 2);
        playerEntity = new PlayerEntity(world, texture, position);
        stage.addActor(playerEntity);
    }

    @Override
    public void hide() {
        playerEntity.detach();
        playerEntity.remove();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
