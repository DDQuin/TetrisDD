package com.ddquin.tetrisdd.tiles;

public class TBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {0, 1, 0},
            {1, 1, 1}
    };


    public TBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.T, LAYOUT);
        centerX = 1;
        centerY = 1;
    }

    public TBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.T, LAYOUT, isGhost);
        centerX = 1;
        centerY = 1;
    }


    @Override
    public Block getGhostBlock() {
        TBlock block = new TBlock(x, y, tileSize, true);
        block.setRotation(rotation);
        return block;
    }

}
