package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.util.Util;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class UIText extends UIObject {

    private Font font;

    private static final int FONT_X_SCALING_FACTOR = 2;

    private static final int FONT_Y_SCALING_FACTOR = 3;

    private String text; // what button says

    private int fontSize;

    private Color outsideColor; // color outside button


    public UIText(float x, float y, int width, int height, Color outsideColor, String text, int fontSize) {
        super(x, y, width, height);
        this.outsideColor = outsideColor;
        this.text = text;
        this.fontSize = fontSize;
        font = Util.getArcadeFont(fontSize);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

        g.setColor(outsideColor);
        g.setFont(font);
        java.util.List<String> strings = text.lines().toList();
        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);
            g.drawString(string, (int) x + width / 2 - string.length() * (fontSize / FONT_X_SCALING_FACTOR), (int) y + height / 2 + fontSize / FONT_Y_SCALING_FACTOR + i * 40);
        }

    }

    @Override
    public void onClick() {
    }

    @Override
    public void onClickOutside() {

    }


    public void setText(String text) {
        this.text = text;
    }


}
