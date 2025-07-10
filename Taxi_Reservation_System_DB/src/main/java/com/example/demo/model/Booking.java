package com.example.demo.model;

import com.example.demo.enums.BookingStatus;
import com.example.demo.enums.LocationPoint;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int taxiId;

    private String customerId;

    @Enumerated(EnumType.STRING)
    private LocationPoint froms;

    @Enumerated(EnumType.STRING)
    private LocationPoint too;

    private int distance;

    private double fare;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking() {}

    public Booking(int taxiId, String customerId, LocationPoint from,
                   LocationPoint to, int distance, double fare) {
        this.taxiId = taxiId;
        this.customerId = customerId;
        this.froms = from;
        this.too = to;
        this.distance = distance;
        this.fare = fare;
        this.status = BookingStatus.BOOKED;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getTaxiId() { return taxiId; }
    public String getCustomerId() { return customerId; }
    public LocationPoint getFrom() { return froms; }
    public LocationPoint getTo() { return too; }
    public int getDistance() { return distance; }
    public double getFare() { return fare; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; 
    }
    }
