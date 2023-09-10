package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.util.Util;

import java.awt.*;


public class UIList extends UIObject {

    private static final int ARC_SCALING_FACTOR = 5;

    private Font font;

    private static final int FONT_X_SCALING_FACTOR = 2;

    private static final int FONT_Y_SCALING_FACTOR = 3;

    private ClickListener clicker;

    private int stroke; // How thick is outside line

    private java.util.List<String> textList; // what button says

    private int fontSize;

    private Color insideColor; // color inside button

    private Color outsideColor; // color outside button


    public UIList(float x, float y, int width, int height, int stroke, Color insideColor, Color outsideColor, java.util.List<String> text, int fontSize, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.stroke = stroke;
        this.insideColor = insideColor;
        this.outsideColor = outsideColor;
        this.textList = text;
        this.fontSize = fontSize;
        font = Util.getArcadeFont(fontSize);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(outsideColor);
        g.fillRoundRect((int) x, (int) y, width, height, width / ARC_SCALING_FACTOR, width / ARC_SCALING_FACTOR);

        g.setColor(insideColor);
        g.fillRoundRect((int) x + stroke / 2, (int) y + stroke / 2, width - stroke, height - stroke, width / 5, width / 5);

        g.setFont(font);
        g.setColor(outsideColor);

        for (int i = 0; i < textList.size(); i++) {
            String text = textList.get(i);
            int yOffset = i * height/textList.size() + 40;
            int oldX = (int) x + width / 2 - text.length() * (fontSize / FONT_X_SCALING_FACTOR);
            g.drawString(text, (int)x + 20, (int) y + yOffset);

        }

    }

    @Override
    public void onClick() {
        clicker.onClick(this);
    }


    public void setTextList(java.util.List<String> text) {
        this.textList = text;
    }
}
