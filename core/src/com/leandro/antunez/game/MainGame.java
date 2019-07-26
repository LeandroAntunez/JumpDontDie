package com.leandro.antunez.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {

    private AssetManager assetManager;

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        assetManager.load("spike.png", Texture.class);
        assetManager.load("gameover.png", Texture.class);
        assetManager.load("floor.png", Texture.class);
        assetManager.load("overfloor.png", Texture.class);
        assetManager.load("minijoe.png", Texture.class);
        assetManager.finishLoading();
        setScreen(new GameScreen(this));
    }
}
