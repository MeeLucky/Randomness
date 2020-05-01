package com.example.randomness;

import java.util.Random;

public class Randomness {
    private static Random rand = new Random();

    static int getRandom(int min, int max) {
        if(min == max) return min;
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return rand.nextInt((max - min) + 1) + min;
    }

    private static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "z", "x", "y", "z"};
    private static String[] alphabetUP = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Z", "X", "Y", "Z"};
    private static String[] special = {"#", "*", "!", "?", ".", ":", "^", "$", "(", ")", "-", "_", "=", "+", "@", "+", "<", ">"};
    private static String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};//чтобы потом не приводить к строке

    public static String getPassword(int length, boolean[] settings) {
        int len = 0;
        for(boolean item : settings) {
            len += item ? 1 : 0;
        }

        if(len == 0) return "";

        String[][] symbols = new String[len][];
        len--;

        if(settings[0]) {
            symbols[len] = alphabetUP;
            len--;
        }

        if(settings[1]) {
            symbols[len] = alphabet;
            len--;
        }

        if(settings[2]) {
            symbols[len] = special;
            len--;
        }

        if(settings[3])
            symbols[len] = numbers;

        len = symbols.length;
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < length; i++) {
            int r1 = getRandom(0,len-1);
            int r2 = getRandom(0, symbols[r1].length-1);
            str.append(symbols[r1][r2]);
        }

        return str.toString();
    }
}
