package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.util.Util;

import java.awt.*;

public class UIButton extends UIObject {

    private static final int ARC_SCALING_FACTOR = 5;

    private Font font;

    private static final int FONT_X_SCALING_FACTOR = 2;

    private static final int FONT_Y_SCALING_FACTOR = 3;

    private ClickListener clicker;

    private int stroke; // How thick is outside line

    private String text; // what button says

    private int fontSize;

    private Color insideColor; // color inside button

    private Color outsideColor; // color outside button


    public UIButton(float x, float y, int width, int height, int stroke, Color insideColor, Color outsideColor, String text, int fontSize, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.stroke = stroke;
        this.insideColor = insideColor;
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
        g.setColor(hovering ? outsideColor.darker() : outsideColor);
        g.fillRoundRect((int) x, (int) y, width, height, width / ARC_SCALING_FACTOR, width / ARC_SCALING_FACTOR);

        g.setColor(hovering ? insideColor.darker() : insideColor);
        g.fillRoundRect((int) x + stroke / 2, (int) y + stroke / 2, width - stroke, height - stroke, width / 5, width / 5);

        g.setFont(font);
        g.setColor(hovering ? outsideColor.darker() : outsideColor);
        g.drawString(text, (int) x + width / 2 - text.length() * (fontSize / FONT_X_SCALING_FACTOR), (int) y + height / 2 + fontSize / FONT_Y_SCALING_FACTOR);
    }

    @Override
    public void onClick() {
        clicker.onClick(this);
    }

    @Override
    public void onClickOutside() {

    }


    public void setText(String text) {
        this.text = text;
    }
}
