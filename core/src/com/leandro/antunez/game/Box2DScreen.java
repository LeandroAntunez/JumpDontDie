package com.leandro.antunez.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen {

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Fixture minijoeFixture;
    private Fixture pinchoFixture;
    private Body minijoeBody;
    private Body pinchoBody;
    private Body sueloBody;
    private Fixture sueloFixture;
    private boolean haColisionado;

    public Box2DScreen(MainGame game) {
        super(game);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32, 18);
        camera.translate(0, 1);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();
                if (fixtureA == minijoeFixture && fixtureB == sueloFixture){
                    haColisionado = true;
                }
                if (fixtureB == minijoeFixture && fixtureA == sueloFixture){
                    haColisionado = true;
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




        BodyDef pinchoDef = createPinchoBodyDef(0.5f);
        pinchoBody = world.createBody(pinchoDef);
        pinchoFixture = createPinchoFixture(pinchoBody);

        BodyDef sueloDef = createSueloBodyDef();
        sueloBody = world.createBody(sueloDef);
        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500, 1);
        sueloFixture = sueloBody.createFixture(sueloShape, 1);

        BodyDef minijoeDef = createJoeBodyDef();
        minijoeBody = world.createBody(minijoeDef);
        PolygonShape minijoeShape = new PolygonShape();
        minijoeShape.setAsBox(0.5f, 0.5f);
        minijoeFixture = minijoeBody.createFixture(minijoeShape, 1);
        sueloShape.dispose();
        minijoeShape.dispose();
    }

    private void saltar(){
        Vector2 position = minijoeBody.getPosition();
        minijoeBody.applyLinearImpulse(0,5, position.x, position.y, true);
    }

    private BodyDef createPinchoBodyDef(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x, 0.5f);
        return def;
    }

    private Fixture createPinchoFixture(Body pinchoBody){
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return pinchoBody.createFixture(shape, 1);
    }

    private BodyDef createSueloBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, -1);
        return def;
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

        if (Gdx.input.justTouched() || haColisionado){
            haColisionado = false;
            saltar();
        }

        world.step(delta, 6, 2);
        camera.update();
        renderer.render(world, camera.combined);

    }

    @Override
    public void dispose() {
        minijoeBody.destroyFixture(minijoeFixture);
        sueloBody.destroyFixture(sueloFixture);
        pinchoBody.destroyFixture(pinchoFixture);
        world.destroyBody(pinchoBody);
        world.destroyBody(sueloBody);
        world.destroyBody(minijoeBody);
        world.dispose();
        renderer.dispose();
    }
}
