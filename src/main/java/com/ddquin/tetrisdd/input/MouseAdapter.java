package com.ddquin.tetrisdd.input;

import java.awt.event.MouseEvent;

public interface MouseAdapter {

     //Return true if want to remove current mouse adapter

     boolean mouseDragged(MouseEvent e);
     boolean mouseMoved(MouseEvent e);
     boolean mouseClicked(MouseEvent e);
     boolean mousePressed(MouseEvent e);
     boolean mouseReleased(MouseEvent e);
     boolean mouseEntered(MouseEvent e);
     boolean mouseExited(MouseEvent e);

}
