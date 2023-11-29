package com.ddquin.tetrisdd.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;


    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public abstract void onClickOutside();

    public void keyTyped(KeyEvent e) {

    }

    public void onMouseMove(MouseEvent e) {
        hovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseReleased(MouseEvent e) {
        if (hovering) {
            onClick();
        } else {
            onClickOutside();
        }

    }


}
