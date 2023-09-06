package com.ddquin.tetrisdd;

import com.ddquin.tetrisdd.display.Display;
import com.ddquin.tetrisdd.input.KeyManager;
import com.ddquin.tetrisdd.input.MouseManager;
import com.ddquin.tetrisdd.states.MenuState;
import com.ddquin.tetrisdd.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    private String title;

    private boolean running = false;
    private Thread thread;
    private int FPS = 60;
    private double timePerTick = 1000000000 / FPS;

    private BufferStrategy bufferStrategy;
    private Graphics g;

    //States
    public State menuState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        menuState = new MenuState(this);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();
        if (State.getState() != null) State.getState().tick();
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bufferStrategy.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        // Draw Here

        if (State.getState() != null) State.getState().render(g);

        //End Drawing
        bufferStrategy.show();
        g.dispose();
    }


    @Override
    public void run() {
        init();
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;

        while(running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / timePerTick;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stopThread();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public synchronized void startThread() {
        if(running) // If the thread is already started, return
            return; // as we don't want another thread.
        running = true;
        thread = new Thread(this);
        thread.start(); // Starts the run method
    }

    public synchronized void stopThread() {
        if(!running) //Same concept but for stopping the thread.
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
