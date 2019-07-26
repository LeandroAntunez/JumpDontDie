package com.leandro.antunez.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {

    private AssetManager assetManager;

    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private MenuScreen menuScreen;
    private LoadingScreen loadingScreen;

    AssetManager getAssetManager() {
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
        assetManager.load("die.ogg", Sound.class);
        assetManager.load("song.ogg", Music.class);
        assetManager.load("jump.ogg", Sound.class);
        assetManager.load("gameover.png", Texture.class);
        assetManager.load("logo.png", Texture.class);
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    public void finishLoading() {
        gameOverScreen = new GameOverScreen(this);
        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public GameOverScreen getGameOverScreen(){
        return gameOverScreen;
    }

    public Screen getMenuScreen() {
        return menuScreen;
    }
}
