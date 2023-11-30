package com.ddquin.tetrisdd.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;

    protected boolean hidden;
    protected boolean hovering = false;


    public UIObject(float x, float y, int width, int height) {
        this.hidden = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void show() {
        hidden = false;
    }

    public void hide() {
        hidden = true;
    }

    public void setX(float x) {
        this.x = x;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public void setY(float y) {
        this.y = y;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void renderTemplate(Graphics g) {
        if (!hidden) {
            render(g);
        }
    }

    public abstract void onClick();

    public abstract void onClickOutside();

    public void keyTyped(KeyEvent e) {

    }

    public void onMouseMove(MouseEvent e) {
        if (hidden) return;
        hovering = false;
        if (bounds.contains(e.getX(), e.getY())) {
            hovering = true;
        }
    }

    public void onMouseReleased(MouseEvent e) {
        if (hidden) return;
        if (hovering) {
            onClick();
        } else {
            onClickOutside();
        }

    }


}
