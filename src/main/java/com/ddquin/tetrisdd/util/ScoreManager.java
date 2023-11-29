package com.ddquin.tetrisdd.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class ScoreManager {

    private static final String SCORE_FILE = "TetrisDD_scores.txt";

    private static final Comparator<PlayerScore> comp = Comparator
            .comparingInt(PlayerScore::score).reversed()
            .thenComparing(PlayerScore::name);

    record PlayerScore(String name, int score) {}


    public ScoreManager() {}

    private List<PlayerScore> getScoresFromFile() {
        List<PlayerScore> playerScores = new ArrayList<>();

        File scoreFile = new File(SCORE_FILE);

        if (!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Scanner myReader;
        try {
            myReader = new Scanner(new File(SCORE_FILE));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (myReader.hasNextLine()) {
            String currentRecord = myReader.nextLine();
            String[] split = currentRecord.split(",");
            playerScores.add(new PlayerScore(split[0], Integer.parseInt(split[1])));
        }
        myReader.close();

        playerScores.sort(comp);

        return playerScores;
    }

    public void addScore(String name, int score) {
        try(FileWriter fileWriter = new FileWriter(SCORE_FILE, true)) {
            fileWriter.append(name + "," + score + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getPlayerScoresDisplay() {
        List<String> displayScores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            displayScores.add(i + 1 + ". " + 0);
        }
        List<PlayerScore> playerScores = getScoresFromFile().stream().limit(5).toList();
        for (int i = 0; i < playerScores.size(); i++) {
            PlayerScore playerScore = playerScores.get(i);
            displayScores.set(i, i + 1 + ". " + playerScore.name + " " + playerScore.score);
        }
        return displayScores;
    }
}
