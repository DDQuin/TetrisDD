package com.ddquin.tetrisdd.tiles;

public class SnakeLeftBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 1, 0},
            {0, 1, 1}
    };
    public SnakeLeftBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.SNAKE_LEFT, LAYOUT);
    }

}
