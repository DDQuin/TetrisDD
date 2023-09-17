package com.ddquin.tetrisdd.tiles;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Block {

    protected int x, y;
    protected List<Tile> tiles;

    protected int rotation;

    protected int tileSize;

    protected TileType tileType;

    protected int[][] blockLayout;

    public Block(int x, int y, int tileSize, TileType tileType, int[][] blockLayout) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.tileSize = tileSize;
        this.tileType = tileType;
        this.blockLayout = blockLayout;
        setUpBlockTiles();
    }

    public void nextRotation() {
        rotation = (rotation + 90) % 360;
        //TODO rotate tiles aswell
    }

    private void setUpBlockTiles() {
        tiles = new ArrayList<>();
        System.out.println(blockLayout.length);
        for (int yBlock = 0; yBlock < blockLayout.length; yBlock++) {
            for (int xBlock = 0; xBlock < blockLayout[0].length; xBlock++) {
                int curTile = blockLayout[yBlock][xBlock];
                if (curTile == 1) {
                    tiles.add(new Tile(x + xBlock, y + yBlock, tileSize, tileType, 2));
                }
            }
        }
    }

    public TileType getTileType() {
        return tileType;
    }

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

    public void moveDown() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveDown).toList();
        setTiles(copyTiles);
        y = y + 1;
    }

    public void moveLeft() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveLeft).toList();
        setTiles(copyTiles);
        x = x + 1;
    }

    public void moveRight() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveRight).toList();
        setTiles(copyTiles);
        x = x - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
