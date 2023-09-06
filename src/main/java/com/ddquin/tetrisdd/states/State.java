package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;

import java.awt.Graphics;


public abstract class State {

    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
        state.playMusic();
    }

    protected abstract void playMusic();

    public static State getState() {
        return currentState;
    }

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
