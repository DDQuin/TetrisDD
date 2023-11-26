package com.ddquin.tetrisdd.tiles;

public class LLeftBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 0, 0},
            {1, 1, 1}
    };
    public LLeftBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.L_LEFT, LAYOUT);
        centerX = 1;
        centerY = 1;
    }

    public LLeftBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.L_LEFT, LAYOUT, isGhost);
        centerX = 1;
        centerY = 1;
    }

    @Override
    public Block getGhostBlock() {
        LLeftBlock block = new LLeftBlock(x, y, tileSize, true);
        block.setRotation(rotation);
        return block;
    }

}
