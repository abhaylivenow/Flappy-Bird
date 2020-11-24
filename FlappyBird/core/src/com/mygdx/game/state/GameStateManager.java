package com.mygdx.game.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<state> states;

    public GameStateManager() {
        states = new Stack<state>();
    }
    public void push(state state){
        states.push(state);
    }
    public void pop(){
        states.pop();
    }
    public void set(state state){
        states.pop().dispose();
        states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
