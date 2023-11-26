package com.ddquin.tetrisdd.tiles;

public class SnakeLeftBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 1, 0},
            {0, 1, 1}
    };
    public SnakeLeftBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.SNAKE_LEFT, LAYOUT);
        centerX = 1;
        centerY = 0;
    }

    public SnakeLeftBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.SNAKE_LEFT, LAYOUT, isGhost);
        centerX = 1;
        centerY = 0;
    }

    @Override
    public Block getGhostBlock() {
        SnakeLeftBlock block = new SnakeLeftBlock(x, y, tileSize, true);
        block.setRotation(rotation);
        return block;
    }

}
