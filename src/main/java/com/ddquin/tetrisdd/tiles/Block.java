package com.ddquin.tetrisdd.tiles;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Block {

    protected int x, y;
    protected List<Tile> tiles;

    protected int rotation;

    protected int tileSize;

    public Block(int x, int y, int tileSize) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.tileSize = tileSize;
        setUpBlockTiles();
    }

    public void nextRotation() {
        rotation = (rotation + 90) % 360;
        //TODO rotate tiles aswell
    }

    abstract void setUpBlockTiles();


    public void tick() {

    }

    public List<Tile> getTilesDown() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveDown).toList();
        return copyTiles;
    }

    public List<Tile> getTilesLeft() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveLeft).toList();
        return copyTiles;
    }

    public List<Tile> getTilesRight() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveRight).toList();
        return copyTiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> newTiles) {
        tiles = newTiles;
    }

    public void render(Graphics g, int xOffset, int yOffset) {
        tiles.forEach(tile -> tile.render(g, xOffset + tile.getX() * tile.getSize(), yOffset + tile.getY() * tile.getSize()));
       // tiles[y][x].render(g, centerX + x * tileSize, centerY + y * tileSize);
    }
}
