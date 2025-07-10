package com.example.demo.model;

import com.example.demo.enums.LocationPoint;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean available;

    @Enumerated(EnumType.STRING)
    private LocationPoint currentLocation;

    public Taxi() {
        this.available = true;
        this.currentLocation = LocationPoint.A;
    }

    public Taxi(LocationPoint location) {
        this.available = true;
        this.currentLocation = location;
    }

    // Getters and Setters
    public int getId() { return id; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public LocationPoint getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(LocationPoint currentLocation) { this.currentLocation = currentLocation; }
}
