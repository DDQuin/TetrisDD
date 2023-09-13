package com.ddquin.tetrisdd.tiles;

import java.awt.*;

public enum TileType {

    BACKGROUND(Color.BLACK),
    BORDER(Color.GRAY),
    L_LEFT(Color.BLUE),
    L_RIGHT(Color.ORANGE),
    SNAKE_LEFT(Color.RED),
    SNAKE_RIGHT(Color.GREEN),
    BOX(Color.YELLOW),
    LINE(Color.CYAN),
    T(Color.MAGENTA);

    Color color;

    TileType(Color c) {
        this.color = c;
    }
}
