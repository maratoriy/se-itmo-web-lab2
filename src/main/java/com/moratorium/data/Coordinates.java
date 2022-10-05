package com.moratorium.data;

public class Coordinates {
    private final double x;
    private final double y;
    private final int r;

    public Coordinates(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public String toString() {
        return String.format("(%.3f; %.3f; %s)", x, y, r);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
