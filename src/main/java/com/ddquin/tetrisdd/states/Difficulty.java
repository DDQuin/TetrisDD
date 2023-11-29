package com.ddquin.tetrisdd.states;

import java.util.Arrays;
import java.util.List;

public enum Difficulty {

    EASY("Easy", 1f),
    MEDIUM("Medium", 2f),
    HARD("Hard", 4f);

    String display;

    float speed;

    Difficulty(String display, float speed) {
        this.display = display;
        this.speed =speed;
    }

    public static Difficulty getNextDifficulty(Difficulty difficulty) {
        List<Difficulty> difficulties = Arrays.stream(Difficulty.values()).toList();
        int curDiffNumIndex = difficulties.indexOf(difficulty);
        int nextDiffNumIndex = (curDiffNumIndex + 1) % difficulties.size();
        return difficulties.get(nextDiffNumIndex);
    }
}
