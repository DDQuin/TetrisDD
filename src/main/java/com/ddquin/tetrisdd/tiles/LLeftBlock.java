package com.ddquin.tetrisdd.tiles;

public class LLeftBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 0, 0},
            {1, 1, 1}
    };
    public LLeftBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.L_LEFT, LAYOUT);
    }

}
