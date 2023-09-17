package com.ddquin.tetrisdd.tiles;

public class SnakeRightBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {0, 1, 1},
            {1, 1, 0}
    };
    public SnakeRightBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.SNAKE_RIGHT, LAYOUT);
    }

}
