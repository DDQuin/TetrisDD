package com.ddquin.tetrisdd.tiles;

public class LineBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 1, 1, 1}
    };
    public LineBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.LINE, LAYOUT);
    }

    public LineBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.LINE, LAYOUT, isGhost);
    }

    @Override
    public Block getGhostBlock() {
        return new LineBlock(x, y, tileSize, true);
    }

}
