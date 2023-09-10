package com.ddquin.tetrisdd.states;

import java.util.Arrays;
import java.util.List;

public enum Difficulty {

    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    String display;

    Difficulty(String display) {
        this.display = display;
    }

    public static Difficulty getNextDifficulty(Difficulty difficulty) {
        List<Difficulty> difficulties = Arrays.stream(Difficulty.values()).toList();
        int curDiffNumIndex = difficulties.indexOf(difficulty);
        int nextDiffNumIndex = (curDiffNumIndex + 1) % difficulties.size();
        return difficulties.get(nextDiffNumIndex);
    }
}
