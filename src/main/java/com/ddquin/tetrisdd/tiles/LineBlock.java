package com.ddquin.tetrisdd.tiles;

public class LineBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 1, 1, 1}
    };
    public LineBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.LINE, LAYOUT);
        centerX = 1;
        centerY = 0;
    }

    public LineBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.LINE, LAYOUT, isGhost);
        centerX = 1;
        centerY = 0;
    }

    @Override
    public Block getGhostBlock() {
        LineBlock block = new LineBlock(x, y, tileSize, true);
        block.setRotation(rotation);
        return block;
    }

}
