package com.ddquin.tetrisdd.tiles;

import java.awt.*;

public class Tile {

    private int x, y;

    private int size;

    private TileType tileType;

    private int insideStroke;

    private boolean isGhost;

    public Tile(int x, int y, int size, TileType tileType, int insideStroke) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.tileType = tileType;
        this.isGhost = false;
        this.insideStroke = insideStroke;
    }

    public boolean isGhost() {
        return isGhost;
    }

    public Tile(int x, int y, int size, TileType tileType, int insideStroke, boolean isGhost) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.tileType = tileType;
        this.insideStroke = insideStroke;
        this.isGhost = isGhost;
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
        return new Tile(x, y + 1, size, tileType, insideStroke, isGhost);
    }

    public Tile moveLeft() {
        return new Tile(x - 1, y, size, tileType, insideStroke, isGhost);
    }

    public Tile rotateClockwise(int xOffset, int yOffset) {
        // Taken from  https://math.stackexchange.com/questions/2138328/90-degree-counter-clockwise-rotation-around-a-point
        if (tileType == TileType.BOX) {
            return new Tile(x, y, size, tileType, insideStroke, isGhost);
        }

        return new Tile(-(y - yOffset) + xOffset, (x - xOffset) + yOffset, size, tileType, insideStroke, isGhost);
    }



    public Tile moveRight() {
        return new Tile(x + 1, y , size, tileType, insideStroke, isGhost);
    }



    public void render(Graphics g, int x, int y) {
        if (isGhost)  {
            g.setColor(tileType.color);
            g.drawRect(x, y, size, size);
        } else {
            g.setColor(tileType.color.darker());
            g.fillRect(x, y, size, size);

            g.setColor(tileType.color);
            g.fillRect(x + insideStroke, y + insideStroke, size - insideStroke * 2, size - insideStroke * 2);
        }
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
