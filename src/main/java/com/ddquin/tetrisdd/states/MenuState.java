package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.audio.AudioPlayer;
import com.ddquin.tetrisdd.ui.ClickListener;
import com.ddquin.tetrisdd.ui.UIManager;
import com.ddquin.tetrisdd.ui.UIStartButton;

import java.awt.Graphics;

public class MenuState extends State {

    private UIManager uiManager;
    private AudioPlayer bgMusic;

    public MenuState(Game game) {
        super(game);
        bgMusic = new AudioPlayer("music/overworld.mp3");

        uiManager = new UIManager(game);
        game.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIStartButton(200,200,128,64, () -> {
            bgMusic.stop();
            game.getMouseManager().setUIManager(null);
            State.setState(game.menuState); //TODO change to game state
        }));

    }

    @Override
    public void tick() {
        uiManager.tick();


    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    @Override
    protected void playMusic() {
        bgMusic.play();
    }

}
