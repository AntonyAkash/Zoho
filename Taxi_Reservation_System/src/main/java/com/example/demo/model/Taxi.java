package com.example.demo.model;

import com.example.demo.enums.LocationPoint;

public class Taxi {
	private int id;
    private boolean available;
    private LocationPoint currentLocation;

    public Taxi(int id) {
        this.id = id;
        this.available = true;
        this.currentLocation = LocationPoint.A; // All taxis start at A
    }

    // Getters and Setters
    public int getId() { return id; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public LocationPoint getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(LocationPoint location) { this.currentLocation = location; }
}
