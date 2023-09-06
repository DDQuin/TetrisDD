package com.ddquin.tetrisdd;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("TetrisDD", 640 , 480);
        game.startThread();
    }
}
