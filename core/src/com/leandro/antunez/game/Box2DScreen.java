package com.leandro.antunez.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen {

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Fixture minijoeFixture;
    private Body minijoeBody;

    public Box2DScreen(MainGame game) {
        super(game);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32, 18);

        BodyDef minijoeDef = createJoeBodyDef();
        minijoeBody = world.createBody(minijoeDef);

        PolygonShape minijoeShape = new PolygonShape();
        minijoeShape.setAsBox(1, 1);
        minijoeFixture = minijoeBody.createFixture(minijoeShape, 1);
        minijoeShape.dispose();
    }

    private BodyDef createJoeBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta, 6, 2);
        renderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        world.destroyBody(minijoeBody);
        renderer.dispose();
    }
}
