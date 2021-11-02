package com.wolfisc.fakesensordevice.utils;

public class Math {

    public static Double round(Double d) {
        String t = String.format("%.2f", d);
        Double a = Double.valueOf(t);
        return a;
    }
}
