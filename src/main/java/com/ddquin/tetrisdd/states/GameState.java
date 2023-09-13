package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.audio.AudioPlayer;
import com.ddquin.tetrisdd.tiles.Block;
import com.ddquin.tetrisdd.tiles.Tile;
import com.ddquin.tetrisdd.tiles.TileType;
import com.ddquin.tetrisdd.ui.UIButton;
import com.ddquin.tetrisdd.ui.UIManager;
import com.ddquin.tetrisdd.ui.UIObject;

import java.awt.*;
import java.util.Queue;
import java.util.Stack;

public class GameState extends State {

    private AudioPlayer bgMusic;

    private UIManager uiManager;

    private Difficulty difficulty;

    private String playerName;

    private int playerScore;

    private boolean goingMenu;

    private Tile[][] tiles;

    private Block currentBlock;

    private Queue<Block> nextBlocks;

    private int boardWidth, boardHeight, tileSize;

    private static final int BUTTON_WIDTH = 276;
    private static final int BUTTON_HEIGHT = 64;

    public GameState(Game game, Difficulty difficulty, String name, int boardWidth, int boardHeight, int tileSize) {
        super(game);
        goingMenu = false;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.tileSize = tileSize;
        this.playerName = name;
        this.difficulty = difficulty;
        drawUI();

        tiles = new Tile[boardHeight][boardWidth];
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                tiles[y][x] = new Tile(x, y, tileSize, TileType.BORDER, 2);
            }
        }

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

        int centerX = game.getWidth() / 2 - boardWidth * tileSize + boardWidth * tileSize / 2;
        int centerY = game.getHeight() / 2 - boardHeight * tileSize + boardHeight * tileSize / 2;


        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                tiles[y][x].render(g, centerX + x * tileSize, centerY + y * tileSize);
            }
        }

        uiManager.render(g);
    }

    @Override
    protected void playMusic() {

    }

    private void drawUI() {
        bgMusic = new AudioPlayer();

        uiManager = new UIManager(game);
        game.getMouseManager().addMouseAdapter(uiManager);

        int centerX = game.getWidth() / 2 - BUTTON_WIDTH + BUTTON_WIDTH / 2;
        int centerY = game.getHeight() / 2 - BUTTON_HEIGHT + BUTTON_HEIGHT / 2;
        uiManager.addObject(new UIButton(centerX - 400, centerY - 100, BUTTON_WIDTH, BUTTON_HEIGHT,
                8, Color.BLACK, Color.WHITE, "Menu", 40,
                (UIObject uiButton) -> {
                    bgMusic.setSound("music/blipSelect.wav");
                    bgMusic.play();
                    goingMenu = true;
                }));
    }

}
