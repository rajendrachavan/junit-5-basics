package com.neo.junit5basics;

public class MathUtils {

    public int add(int x, int y) {
        return x + y;
    }

    public int substract(int x, int y) {
        return x - y;
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    public int divide(int x, int y) {
        return x / y;
    }

    public double computeCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
}
