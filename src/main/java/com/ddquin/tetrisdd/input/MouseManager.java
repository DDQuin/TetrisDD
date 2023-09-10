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
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mouseMoved(e);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }

        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mousePressed(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }

        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mouseReleased(e);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (MouseAdapter mouseAdapter : mouseAdapters) {
            mouseAdapter.mouseExited(e);
        }
    }

}
