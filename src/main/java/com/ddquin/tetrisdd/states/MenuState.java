package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.audio.AudioPlayer;
import com.ddquin.tetrisdd.ui.*;
import com.ddquin.tetrisdd.util.ScoreManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    private AudioPlayer audioPlayer;

    private ScoreManager scoreManager;

    private UIInputField inputField;

    private boolean goingGame;

    private Difficulty difficulty;

    private static final int BUTTON_WIDTH = 276;
    private static final int BUTTON_HEIGHT = 64;

    public MenuState(Game game) {
        super(game);
        scoreManager = new ScoreManager();
        goingGame = false;
        difficulty = Difficulty.EASY;
        audioPlayer = new AudioPlayer();

        uiManager = new UIManager(game);
        game.getMouseManager().addMouseAdapter(uiManager);
        game.getKeyManager().addKeyAdapter(uiManager);

        int centerX = game.getWidth() / 2 - BUTTON_WIDTH + BUTTON_WIDTH / 2;
        int centerY = game.getHeight() / 2 - BUTTON_HEIGHT + BUTTON_HEIGHT / 2;

        uiManager.addObject(new UIButton(centerX, centerY - 320, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "Start", 40,
                (UIObject uiButton) -> {
                    audioPlayer.setSound("music/blipSelect.wav");
                    audioPlayer.play();
                    goingGame = true;
                }));

        uiManager.addObject(new UIButton(centerX, centerY - 230, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "Quit", 40,
                (UIObject uiButton) -> {
                    audioPlayer.setSound("music/blipSelect.wav");
                    audioPlayer.play();
                    System.exit(0);
                }));

        inputField = new UIInputField(centerX, centerY - 140, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "NAME", 40,
                (UIObject uiButton) -> {}, game.getFPS(), 6
        );
        uiManager.addObject(inputField);

        uiManager.addObject(new UIList(centerX, centerY - 50, BUTTON_WIDTH, 360,
                8, Color.BLACK, Color.WHITE, scoreManager.getPlayerScoresDisplay(), 15, (UIObject ui) -> {}
                ));

        uiManager.addObject(new UIButton(centerX, centerY + 320, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, difficulty.display, 40,
                (UIObject uiButton) -> {
                    difficulty = Difficulty.getNextDifficulty(difficulty);
                    ((UIButton) uiButton).setText(difficulty.display);
                }));

    }

    @Override
    public void tick() {
        uiManager.tick();
        if (goingGame) State.setState(new GameState(game, difficulty, inputField.getText()));

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
