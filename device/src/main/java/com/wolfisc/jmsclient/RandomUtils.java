package com.wolfisc.jmsclient;

public class RandomUtils {

    public static double randDouble(double min, double max) {
        return Math.random() * max + min;
    }

    public static int randInt(int min, int max) {
        return (int) (Math.random() * max + min);
    }
}
