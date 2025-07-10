package com.example.demo.enums;

public enum LocationPoint {
	A(0), B(15), C(30), D(45), E(60), F(75);

    private final int kmFromA;

    LocationPoint(int kmFromA) {
        this.kmFromA = kmFromA;
    }

    public int getKmFromA() {
        return kmFromA;
    }

    public int getDistance(LocationPoint other) {
        return Math.abs(this.kmFromA - other.kmFromA);
    }
}
