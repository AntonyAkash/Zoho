package com.example.demo.model;

import com.example.demo.enums.BookingStatus;
import com.example.demo.enums.LocationPoint;

public class Booking {
	 private int id;
	    private int taxiId;
	    private String customerId;
	    private LocationPoint from;
	    private LocationPoint to;
	    private int distance;
	    private double fare;
	    private BookingStatus status;

	    public Booking(int id, int taxiId, String customerId,
	                   LocationPoint from, LocationPoint to,
	                   int distance, double fare) {
	        this.id = id;
	        this.taxiId = taxiId;
	        this.customerId = customerId;
	        this.from = from;
	        this.to = to;
	        this.distance = distance;
	        this.fare = fare;
	        this.status = BookingStatus.BOOKED;
	    }

	    // Getters and Setters
	    public int getId() { return id; }
	    public int getTaxiId() { return taxiId; }
	    public String getCustomerId() { return customerId; }
	    public LocationPoint getFrom() { return from; }
	    public LocationPoint getTo() { return to; }
	    public int getDistance() { return distance; }
	    public double getFare() { return fare; }
	    public BookingStatus getStatus() { return status; }
	    public void setStatus(BookingStatus status) { this.status = status; }
}
