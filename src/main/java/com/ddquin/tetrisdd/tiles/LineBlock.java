package com.ddquin.tetrisdd.tiles;

public class LineBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 1, 1, 1}
    };
    public LineBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.LINE, LAYOUT);
    }

}
