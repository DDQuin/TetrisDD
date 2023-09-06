package com.ddquin.tetrisdd.ui;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UIStartButton extends UIObject{

    private ClickListener clicker;

    public UIStartButton(float x, float y, int width, int height, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;

    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        if (hovering) {
            g.setColor(Color.BLACK);
            g.fillRect((int) x, (int) y, width, height);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect((int) x, (int) y, width, height);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }



}
