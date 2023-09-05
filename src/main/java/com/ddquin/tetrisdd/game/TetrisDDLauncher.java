package com.ddquin.tetrisdd.game;

import javax.swing.*;

public class TetrisDDLauncher {

    public void startGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TetrisDD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
