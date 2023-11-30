package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.input.KeyAdapter;
import com.ddquin.tetrisdd.input.MouseAdapter;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class UIManager implements MouseAdapter, KeyAdapter {

    private Game game;
    private ArrayList<UIObject> objects;

    public UIManager(Game game) {
        this.game = game;
        objects = new ArrayList<>();
    }

    public void tick() {
        for (UIObject o : objects) {
            o.tick();
        }

    }

    public void render(Graphics g) {
        for (UIObject o : objects) {
            o.renderTemplate(g);
        }

    }


    public void addObject(UIObject o) {
        objects.add(o);
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseMove(e);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (UIObject o : objects) {
            o.keyTyped(e);
        }
    }
}
