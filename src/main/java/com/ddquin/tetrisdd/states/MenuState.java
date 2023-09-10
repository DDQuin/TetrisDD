package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.audio.AudioPlayer;
import com.ddquin.tetrisdd.ui.UIList;
import com.ddquin.tetrisdd.ui.UIManager;
import com.ddquin.tetrisdd.ui.UIButton;
import com.ddquin.tetrisdd.ui.UIObject;
import com.ddquin.tetrisdd.util.ScoreManager;
import com.ddquin.tetrisdd.util.Util;

import java.awt.*;
import java.util.ArrayList;

public class MenuState extends State {

    private UIManager uiManager;
    private AudioPlayer bgMusic;

    private ScoreManager scoreManager;

    private boolean goingGame;

    private Difficulty difficulty;

    private static final int BUTTON_WIDTH = 276;
    private static final int BUTTON_HEIGHT = 64;

    public MenuState(Game game) {
        super(game);
        scoreManager = new ScoreManager();
        goingGame = false;
        difficulty = Difficulty.EASY;
        bgMusic = new AudioPlayer();

        uiManager = new UIManager(game);
        game.getMouseManager().addMouseAdapter(uiManager);
        int centerX = game.getWidth() / 2 - BUTTON_WIDTH + BUTTON_WIDTH / 2;
        int centerY = game.getHeight() / 2 - BUTTON_HEIGHT + BUTTON_HEIGHT / 2;
        uiManager.addObject(new UIButton(centerX, centerY - 300, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "Start", 40,
                (UIObject uiButton) -> {
                    bgMusic.setSound("music/blipSelect.wav");
                    bgMusic.play();
                    goingGame = true;
                }));

        uiManager.addObject(new UIButton(centerX, centerY - 200, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "Quit", 40,
                (UIObject uiButton) -> {
                    bgMusic.setSound("music/blipSelect.wav");
                    bgMusic.play();
                    System.exit(0);
                }));

        uiManager.addObject(new UIList(centerX, centerY - 100, BUTTON_WIDTH, 360,
                8, Color.BLACK, Color.WHITE, scoreManager.getPlayerScoresDisplay(), 15, (UIObject ui) -> {}
                ));

        uiManager.addObject(new UIButton(centerX, centerY + 300, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, difficulty.display, 40,
                (UIObject uiButton) -> {
                    difficulty = Difficulty.getNextDifficulty(difficulty);
                    ((UIButton) uiButton).setText(difficulty.display);
                }));

    }

    @Override
    public void tick() {
        uiManager.tick();
        if (goingGame) State.setState(new GameState(game, difficulty));

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
