package com.ddquin.tetrisdd;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("TetrisDD", 1280 , 720);
        game.startThread();
    }
}
