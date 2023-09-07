package com.ddquin.tetrisdd.input;


import com.ddquin.tetrisdd.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private List<MouseAdapter> mouseAdapters;

    public MouseManager() {
        mouseAdapters = new ArrayList<>();
    }

    public void addMouseAdapter(MouseAdapter mouseAdapter) {
        mouseAdapters.add(mouseAdapter);
    }


    //getters
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mouseDragged(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mouseMoved(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mouseClicked(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }

        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mousePressed(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }

        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mouseReleased(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mouseEntered(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Set<MouseAdapter> adaptersToRemove = new HashSet<>();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            if (mouseAdapter.mouseExited(e)) adaptersToRemove.add(mouseAdapter);
        }
        mouseAdapters.removeAll(adaptersToRemove);
    }

}
