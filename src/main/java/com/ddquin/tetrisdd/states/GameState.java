package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.audio.AudioPlayer;
import com.ddquin.tetrisdd.ui.UIButton;
import com.ddquin.tetrisdd.ui.UIManager;
import com.ddquin.tetrisdd.ui.UIObject;

import java.awt.*;

public class GameState extends State {

    private AudioPlayer bgMusic;

    private UIManager uiManager;

    private Difficulty difficulty;

    private boolean goingMenu;

    private static final int BUTTON_WIDTH = 276;
    private static final int BUTTON_HEIGHT = 64;

    public GameState(Game game, Difficulty difficulty) {
        super(game);
        goingMenu = false;
        this.difficulty = difficulty;
        bgMusic = new AudioPlayer();

        uiManager = new UIManager(game);
        game.getMouseManager().addMouseAdapter(uiManager);

        int centerX = game.getWidth() / 2 - BUTTON_WIDTH + BUTTON_WIDTH / 2;
        int centerY = game.getHeight() / 2 - BUTTON_HEIGHT + BUTTON_HEIGHT / 2;
        uiManager.addObject(new UIButton(centerX, centerY - 100, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "Menu", 40,
                (UIObject uiButton) -> {
                    bgMusic.setSound("music/blipSelect.wav");
                    bgMusic.play();
                    goingMenu = true;
                }));

    }

    @Override
    public void tick() {
        uiManager.tick();
        if (goingMenu) State.setState(new MenuState(game));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        uiManager.render(g);
    }

    @Override
    protected void playMusic() {

    }

}
