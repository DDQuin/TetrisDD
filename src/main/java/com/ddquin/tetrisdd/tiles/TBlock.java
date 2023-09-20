package com.ddquin.tetrisdd.tiles;

public class TBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {0, 1, 0},
            {1, 1, 1}
    };
    public TBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.T, LAYOUT);
    }

    public TBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.T, LAYOUT, isGhost);
    }


    @Override
    public Block getGhostBlock() {
        return new TBlock(x, y, tileSize, true);
    }

}
