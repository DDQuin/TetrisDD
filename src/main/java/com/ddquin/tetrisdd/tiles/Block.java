package com.ddquin.tetrisdd.tiles;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Block {

    protected int x, y;
    protected List<Tile> tiles;

    protected int rotation;

    protected int tileSize;

    protected int centerX;

    protected int centerY;

    protected TileType tileType;

    protected int[][] blockLayout;

    protected boolean isGhost;

    public Block(int x, int y, int tileSize, TileType tileType, int[][] blockLayout) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.tileSize = tileSize;
        this.tileType = tileType;
        this.blockLayout = blockLayout;
        this.isGhost = false;
        setUpBlockTiles();
    }

    public int getRotation() {
        return rotation;
    }

    public Block(int x, int y, int tileSize, TileType tileType, int[][] blockLayout, boolean isGhost) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.tileSize = tileSize;
        this.tileType = tileType;
        this.blockLayout = blockLayout;
        this.isGhost = isGhost;
        setUpBlockTiles();
    }

    public abstract Block getGhostBlock();


    public boolean isGhost() {
        return isGhost;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    private void setUpBlockTiles() {
        tiles = new ArrayList<>();
        for (int yBlock = 0; yBlock < blockLayout.length; yBlock++) {
            for (int xBlock = 0; xBlock < blockLayout[0].length; xBlock++) {
                int curTile = blockLayout[yBlock][xBlock];
                if (curTile == 1) {
                    tiles.add(new Tile(x + xBlock, y + yBlock, tileSize, tileType, 2, isGhost));
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
        x = x - 1;
    }

    public void moveRight() {
        List<Tile> copyTiles = tiles.stream().map(Tile::moveRight).toList();
        setTiles(copyTiles);
        x = x + 1;
    }

    public List<Tile> getRotatedTiles() {
        List<Tile> copyTiles = tiles.stream().map(t -> t.rotateClockwise(x + centerX, y +centerY)).toList();
        return copyTiles;
    }

    public void rotateTiles() {
        List<Tile> copyTiles = tiles.stream().map(t -> t.rotateClockwise(x + centerX, y +centerY)).toList();
        setTiles(copyTiles);
        rotation = (rotation + 1) % 4;
    }

    public void rotateStartGhost() {
        List<Tile> copyTiles = tiles.stream().map(t -> t.rotateClockwise(x + centerX, y +centerY)).toList();
        setTiles(copyTiles);
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
