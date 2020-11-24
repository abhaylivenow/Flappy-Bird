package com.mygdx.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class state {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public state(GameStateManager gsm) {
        cam = new OrthographicCamera();
        mouse = new Vector3();
        this.gsm = gsm;
    }
    protected abstract void handleInput();
    public abstract void update(Float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
