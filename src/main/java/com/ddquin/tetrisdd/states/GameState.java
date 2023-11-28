package com.ddquin.tetrisdd.states;

import com.ddquin.tetrisdd.Game;
import com.ddquin.tetrisdd.audio.AudioPlayer;
import com.ddquin.tetrisdd.tiles.*;
import com.ddquin.tetrisdd.ui.UIButton;
import com.ddquin.tetrisdd.ui.UIManager;
import com.ddquin.tetrisdd.ui.UIObject;
import com.ddquin.tetrisdd.ui.UIText;

import java.awt.*;
import java.util.*;
import java.util.List;


public class GameState extends State {

    private AudioPlayer bgMusic;

    private UIManager uiManager;

    private UIText scoreText;

    private int stepsToMoveDown;

    private int stepsMoved;

    private List<Tile> tilesToMove;

    private Difficulty difficulty;

    private int ticksPassed;

    private String playerName;

    private int playerScore;

    private boolean goingMenu;

    private Tile[][] tiles;

    private Block currentBlock;
    private Block ghostBlock;

    private Queue<Block> nextBlocks;

    private int ticksKeyDelay;

    private float blockSpeed;

    private float fallSpeed;

    private int boardWidth, boardHeight, tileSize;

    private static final int SCORE = 100;

    private List<List<Tile>> tilesToRemove;

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
        this.blockSpeed = 1f;
        this.fallSpeed = 10f;
        this.ticksKeyDelay = game.getFPS()/ 10;
        this.nextBlocks = new ArrayDeque<>();
        this.tilesToRemove = new ArrayList<>();
        this.tilesToMove = new ArrayList<>();
        drawUI();
        setupBoard();

    }

    @Override
    public void tick() {
        uiManager.tick();
        ticksPassed = (ticksPassed + 1) % game.getFPS();
        int ticksNeededForMove = (int) ((game.getFPS() - 1) / blockSpeed);
        int ticksNeededForFall = (int) ((game.getFPS() - 1) / fallSpeed);
        int ticksPerBlock = 2;
        if (!tilesToRemove.isEmpty() && !tilesToRemove.get(0).isEmpty()) {
            if (ticksPassed % ticksPerBlock == 0) {
                for (List<Tile> tiles: tilesToRemove) {
                    tiles.get(0).setTileType(TileType.BACKGROUND);
                    tiles.remove(0);
                }
            }
            if (tilesToRemove.get(0).isEmpty()) {
                tilesToRemove.clear();
                ticksPassed = 1;
            }
            return;
        }
        while (stepsToMoveDown  > 0) {
            if (ticksPassed % ticksNeededForFall == 0 && ticksPassed != 0) {
                List<Tile> newTilestoAdd = new ArrayList<>();
                for (Tile tile: tilesToMove) {
                    Tile newTile = tiles[tile.getY()+1][tile.getX()];
                    newTile.setTileType(tile.getTileType());
                    newTilestoAdd.add(newTile);
                    tile.setTileType(TileType.BACKGROUND);
                }
                tilesToMove.clear();
                tilesToMove.addAll(newTilestoAdd);
                stepsToMoveDown--;
            }
            return;
        }

        if (ticksPassed % ticksKeyDelay == 0) {
            getInput();
        }
        if (ticksPassed % ticksNeededForMove == 0 && ticksPassed != 0) {
            moveBlockDown();
        }
        showGhost();

        if (goingMenu) State.setState(new MenuState(game));
    }

    private void showGhost() {
        ghostBlock = currentBlock.getGhostBlock();
        for (int i = 0; i < ghostBlock.getRotation(); i++) {
            ghostBlock.rotateStartGhost();
        }
        boolean reachedMost = false;
        while (!reachedMost) {
            List<Tile> nextBlockTiles = ghostBlock.getTilesDown();
            boolean blockWillHitGround = nextBlockTiles.stream().anyMatch(blockTile -> {
                Tile boardTile = tiles[blockTile.getY()][blockTile.getX()];
                return boardTile.getTileType() != TileType.BACKGROUND;
            });
            if (blockWillHitGround) {
                reachedMost = true;
            } else {
                ghostBlock.moveDown();
            }
        }
    }

    private void moveBlockDown() {
        List<Tile> nextBlockTiles = currentBlock.getTilesDown();
        boolean isAlreadyInsideAtStart = currentBlock.getTiles().stream().anyMatch(blockTile -> {
            Tile boardTile = tiles[blockTile.getY()][blockTile.getX()];
            return boardTile.getTileType() != TileType.BACKGROUND;
        });
        if (isAlreadyInsideAtStart) {
            goingMenu = true;
            return;
        }
        boolean blockWillHitGround = nextBlockTiles.stream().anyMatch(blockTile -> {
            Tile boardTile = tiles[blockTile.getY()][blockTile.getX()];
           // System.out.println(currentBlock.getTiles().get(0).getY() + " " + boardTile.getY());
            return boardTile.getTileType() != TileType.BACKGROUND;
        });
        playerScore++;
        if (!blockWillHitGround) currentBlock.moveDown();
        if (blockWillHitGround) placeBlock(currentBlock.getTiles());
    }

    private void getInput() {
        List<Tile> nextBlockTiles;
        boolean tilesWillCollide = false;
        boolean leftMove = true;
        if (game.getKeyManager().aDown) moveBlockDown();
        if (game.getKeyManager().aUp) rotateBlock();
        if (game.getKeyManager().aLeft) {
            nextBlockTiles = currentBlock.getTilesLeft();
        } else if (game.getKeyManager().aRight) {
            nextBlockTiles = currentBlock.getTilesRight();
            leftMove = false;
        } else {
            return;
        }
        tilesWillCollide = nextBlockTiles.stream().anyMatch(blockTile -> {
            Tile boardTile = tiles[blockTile.getY()][blockTile.getX()];
            return boardTile.getTileType() != TileType.BACKGROUND;
        });
        if (!tilesWillCollide) {
            if (leftMove) {
                currentBlock.moveLeft();
            } else {
                currentBlock.moveRight();
            }
        }

    }

    @Override
    public void render(Graphics g) {
        scoreText.setText("Score: " + playerScore);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        int centerX = game.getWidth() / 2 - boardWidth * tileSize + boardWidth * tileSize / 2;
        int centerY = game.getHeight() / 2 - boardHeight * tileSize + boardHeight * tileSize / 2;


        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                tiles[y][x].render(g, centerX + x * tileSize, centerY + y * tileSize);
            }
        }

        currentBlock.render(g, centerX, centerY);
        ghostBlock.render(g, centerX, centerY);

        int i = 0;
        for (Block nextBlock: nextBlocks) {
            nextBlock.render(g, centerX + 350, centerY + 350 + (100 * i));
            i++;
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

        scoreText = new UIText(centerX + 400, centerY - 100, BUTTON_WIDTH, BUTTON_HEIGHT,
                Color.WHITE, "Score: " + playerScore,   40 );
        uiManager.addObject(scoreText);
    }

    private void setupBoard() {
        tiles = new Tile[boardHeight][boardWidth];
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                TileType tileType = TileType.BACKGROUND;
                boolean isBorder = y == 0 || x == 0 || x == boardWidth - 1;
                boolean isGround = y == boardHeight - 1;
                if (isBorder) tileType = TileType.BORDER;
                if (isGround) tileType = TileType.GROUND;
                tiles[y][x] = new Tile(x, y, tileSize, tileType, 2, false);
            }
        }

        //Intilise with 3 random to start
        nextBlocks.add(getRandomBlock());
        nextBlocks.add(getRandomBlock());
        nextBlocks.add(getRandomBlock());
        spawnBlock();

    }

    private void rotateBlock() {
        List<Tile> nextBlockTiles = currentBlock.getRotatedTiles();

        boolean blockWillHitGround = nextBlockTiles.stream().anyMatch(blockTile -> {
            Tile boardTile = tiles[blockTile.getY()][blockTile.getX()];
            // System.out.println(currentBlock.getTiles().get(0).getY() + " " + boardTile.getY());
            return boardTile.getTileType() != TileType.BACKGROUND;
        });
        if (!blockWillHitGround) currentBlock.rotateTiles();
    }

    private void placeBlock(List<Tile> tilesToPlace) {
        for (Tile tile: tilesToPlace) {
            Tile boardTile = tiles[tile.getY()][tile.getX()];
            boardTile.setTileType(tile.getTileType());
        }
        List<Integer> lines = getLinesComplete();
        removeLines(lines);
        playerScore += scoreFunction(lines.size());
        System.out.println("Score is " + scoreFunction(lines.size()));
        spawnBlock();
    }

    private int scoreFunction(int numberOfLines) {
        return (numberOfLines * (numberOfLines + 1) / 2) * SCORE;
    }

    private void removeLines(List<Integer> linesToRemove) {
        if (linesToRemove.isEmpty()) return;
        tilesToRemove.clear();
        System.out.println(linesToRemove);
        for (int y : linesToRemove) {
            List<Tile> tilesToRemoveLine = new ArrayList<>();
            for (int x = 1; x < boardWidth - 1; x++) {
                tilesToRemoveLine.add(tiles[y][x]);
            }
            tilesToRemove.add(tilesToRemoveLine);
        }
        int topY = linesToRemove.get(0);
        int removed = linesToRemove.size();
        System.out.println(topY);
        System.out.println(removed);

        tilesToMove.clear();
        stepsToMoveDown = removed;
        for (int y = topY - 1; y > 0; y--) {
            for (int x = 1; x < boardWidth - 1; x++) {
                tilesToMove.add(tiles[y][x]);
//                Tile boardTile = tiles[y][x];
//                tiles[y + removed][x].setTileType(boardTile.getTileType());
//                boardTile.setTileType(TileType.BACKGROUND);
            }
        }
    }

    private List<Integer> getLinesComplete() {
        List<Integer> linesComplete = new ArrayList<>();
        for (int y = 0; y < boardHeight; y++) {
            boolean isAllTiles = true;
            for (int x = 1; x < boardWidth - 1; x++) {
                Tile boardTile = tiles[y][x];
                if (boardTile.getTileType() == TileType.BACKGROUND || boardTile.getTileType() == TileType.BORDER || boardTile.getTileType() == TileType.GROUND) {
                    isAllTiles = false;
                    break;
                }
            }
            if (isAllTiles) linesComplete.add(y);
        }
        return linesComplete;
    }



    private void spawnBlock() {
        currentBlock = nextBlocks.remove();
        ghostBlock = currentBlock.getGhostBlock();
        nextBlocks.add(getRandomBlock());
      //  nextBlocks.forEach(b -> System.out.println(b.getTileType()));
    }

    private Block getRandomBlock() {
        Random rand = new Random();
        Block randomBlock = new BoxBlock(5, 1, tileSize);
        int block = rand.nextInt(1);
        if (block == 0) randomBlock = new LineBlock( 5, 1, tileSize);
        if (block == 1) randomBlock = new LineBlock(5, 1, tileSize);
        if (block == 2) randomBlock = new LLeftBlock(5, 1, tileSize);
        if (block == 3) randomBlock = new LRightBlock(5, 1, tileSize);
        if (block == 4) randomBlock = new SnakeLeftBlock(5, 1, tileSize);
        if (block == 5) randomBlock = new SnakeRightBlock(5, 1, tileSize);
        if (block == 6) randomBlock = new TBlock(5, 1, tileSize);
        return randomBlock;
    }

    private void gameLost() {
        goingMenu = true;
    }

}
