package com.ddquin.tetrisdd.tiles;

public class LRightBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {0, 0, 1},
            {1, 1, 1}
    };
    public LRightBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.L_RIGHT, LAYOUT);
    }

}
