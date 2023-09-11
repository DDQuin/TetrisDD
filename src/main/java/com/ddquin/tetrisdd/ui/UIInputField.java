package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.util.Util;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UIInputField extends UIObject {

    private static final int ARC_SCALING_FACTOR = 5;

    private Font font;

    private static final int FONT_X_SCALING_FACTOR = 2;

    private static final int FONT_Y_SCALING_FACTOR = 3;

    private ClickListener clicker;

    private int stroke; // How thick is outside line

    private String text; // what field says

    private int fontSize;

    private Color insideColor; // color inside button

    private Color outsideColor; // color outside button

    private boolean focused;

    private int FPS;

    private int ticksSinceFocus;

    private int maxLength;


    public UIInputField(float x, float y, int width, int height, int stroke, Color insideColor, Color outsideColor, String text, int fontSize, ClickListener clicker, int FPS, int maxLength) {
        super(x, y, width, height);
        this.focused = false;
        this.clicker = clicker;
        this.stroke = stroke;
        this.insideColor = insideColor;
        this.outsideColor = outsideColor;
        this.text = text;
        this.fontSize = fontSize;
        this.FPS = FPS;
        this.maxLength = maxLength;
        font = Util.getArcadeFont(fontSize);
    }

    @Override
    public void tick() {
        ticksSinceFocus = (ticksSinceFocus + 1) % FPS;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(hovering ? outsideColor.darker() : outsideColor);
        g.fillRoundRect((int) x, (int) y, width, height, width / ARC_SCALING_FACTOR, width / ARC_SCALING_FACTOR);

        g.setColor(hovering ? insideColor.darker() : insideColor);
        g.fillRoundRect((int) x + stroke / 2, (int) y + stroke / 2, width - stroke, height - stroke, width / 5, width / 5);

        g.setFont(font);
        g.setColor(hovering ? outsideColor.darker() : outsideColor);

        String textToWrite = text.toUpperCase();
        boolean firstHalfSecond = ticksSinceFocus <= FPS/2;
        if (focused && firstHalfSecond && text.length() < maxLength) {
            textToWrite = textToWrite + "_";
        }
        g.drawString(textToWrite, (int) x + width / 2 - text.length() * (fontSize / FONT_X_SCALING_FACTOR), (int) y + height / 2 + fontSize / FONT_Y_SCALING_FACTOR);
    }

    @Override
    public void onClick() {
        clicker.onClick(this);
        focused = true;
        ticksSinceFocus = 0;
    }

    @Override
    public void onClickOutside() {
        focused = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!focused) return;
       char typed = e.getKeyChar();
       boolean isAlpha = typed >= 'a' && typed <= 'z' || typed >= 'A' && typed <= 'Z';
       boolean isBackspace = e.getKeyChar() == KeyEvent.VK_BACK_SPACE;
        if (isAlpha && text.length() < maxLength) {
            text = text + e.getKeyChar();
        } else if (isBackspace && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    public String getText() {
        return text;
    }
}
