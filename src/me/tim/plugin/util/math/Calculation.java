package me.tim.plugin.util.math;

public class Calculation {

    public static boolean isRoughlyEqual(double d, double d1, double place) {
        return Math.abs(d - d1) < place;
    }
}
