package com.ddquin.tetrisdd.tiles;

import java.util.ArrayList;

public class BoxBlock extends Block{

    protected static final int[][] LAYOUT = new int[][]{
            {1, 1},
            {1, 1}
    };


    public BoxBlock(int x, int y, int tileSize) {
        super(x, y, tileSize, TileType.BOX, LAYOUT);
    }

    public BoxBlock(int x, int y, int tileSize, boolean isGhost) {
        super(x, y, tileSize, TileType.BOX, LAYOUT, isGhost);
    }

    @Override
    public Block getGhostBlock() {
        BoxBlock block = new BoxBlock(x, y, tileSize, true);
        block.setRotation(rotation);
        return block;
    }

    //    @Override
    void setUpBlockTiles() {
//        tiles = new ArrayList<>();
//        tiles.add(new Tile(x, y, tileSize, TileType.BOX, 2));
//        tiles.add(new Tile(x + 1, y, tileSize, TileType.BOX, 2));
//        tiles.add(new Tile(x, y + 1, tileSize, TileType.BOX, 2));
//        tiles.add(new Tile(x + 1, y + 1, tileSize, TileType.BOX, 2));
    }
}
