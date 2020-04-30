package com.example.randomness;

import java.util.Random;

public class Randomness {
    private static Random rand = new Random();

    static int getRandom(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return rand.nextInt((max - min) + 1) + min;
    }
}
