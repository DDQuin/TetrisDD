package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.input.MouseAdapter;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class UIManager implements MouseAdapter {

    private Game game;
    private ArrayList<UIObject> objects;

    public UIManager(Game game) {
        this.game = game;
        objects = new ArrayList<>();
    }

    public void tick() {
        for (UIObject o: objects) {
            o.tick();
        }

    }

    public void render(Graphics g) {
        for (UIObject o: objects) {
            o.render(g);
        }

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }

    public void removeObject(UIObject o) {
        objects.remove(o);
    }

    @Override
    public boolean mouseDragged(MouseEvent e) {
        return false;
    }

    @Override
    public boolean mouseMoved(MouseEvent e) {
        boolean shouldRemove = false;
        for (UIObject o: objects) {
            if (o.onMouseMove(e)) shouldRemove = true;
        }
        return shouldRemove;
    }

    @Override
    public boolean mouseClicked(MouseEvent e) {
        return false;
    }

    @Override
    public boolean mousePressed(MouseEvent e) {
        return false;
    }

    @Override
    public boolean mouseReleased(MouseEvent e) {
        boolean shouldRemove = false;
        for (UIObject o: objects) {
            if (o.onMouseReleased(e)) shouldRemove = true;
        }
        return shouldRemove;
    }

    @Override
    public boolean mouseEntered(MouseEvent e) {
        return false;
    }

    @Override
    public boolean mouseExited(MouseEvent e) {
        return false;
    }
}
