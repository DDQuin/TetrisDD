package com.ddquin.tetrisdd.tiles;

import java.awt.*;

public class Tile {

    private int x, y;

    private int size;

    private TileType tileType;

    private int insideStroke;

    public Tile(int x, int y, int size, TileType tileType, int insideStroke) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.tileType = tileType;
        this.insideStroke = insideStroke;
    }

    public void tick() {

    }

    public int getX() {
        return x;
    }

    public int getSize() {
        return size;
    }

    public int getY() {
        return y;
    }

    public Tile moveDown() {
        return new Tile(x, y + 1, size, tileType, insideStroke);
    }

    public Tile moveLeft() {
        return new Tile(x - 1, y, size, tileType, insideStroke);
    }

    public Tile moveRight() {
        return new Tile(x + 1, y , size, tileType, insideStroke);
    }



    public void render(Graphics g, int x, int y) {
        g.setColor(tileType.color.darker());
        g.fillRect(x, y, size, size);
        g.setColor(tileType.color);
        g.fillRect(x + insideStroke, y + insideStroke, size - insideStroke * 2, size - insideStroke * 2);
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
