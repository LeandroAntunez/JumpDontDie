package com.leandro.antunez.game;

import com.badlogic.gdx.InputAdapter;

public class Procesador extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Has tocado en la posicion: " + screenX + "" + screenY);
        System.out.println("Has usado el dedo " + pointer + "y el boton " + button);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
