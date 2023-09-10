package com.ddquin.tetrisdd.input;

import java.awt.event.MouseEvent;

public interface MouseAdapter {

    //Return true if want to remove current mouse adapter

    void mouseDragged(MouseEvent e);

    void mouseMoved(MouseEvent e);

    void mouseClicked(MouseEvent e);

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseEntered(MouseEvent e);

    void mouseExited(MouseEvent e);

}
