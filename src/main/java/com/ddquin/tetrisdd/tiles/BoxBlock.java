package com.ddquin.tetrisdd.tiles;

import java.util.ArrayList;

public class BoxBlock extends Block{
    public BoxBlock(int x, int y, int tileSize) {
        super(x, y, tileSize);
    }

    @Override
    void setUpBlockTiles() {
        tiles = new ArrayList<>();
        tiles.add(new Tile(x, y, tileSize, TileType.BOX, 2));
        tiles.add(new Tile(x + 1, y, tileSize, TileType.BOX, 2));
        tiles.add(new Tile(x, y + 1, tileSize, TileType.BOX, 2));
        tiles.add(new Tile(x + 1, y + 1, tileSize, TileType.BOX, 2));
    }
}
