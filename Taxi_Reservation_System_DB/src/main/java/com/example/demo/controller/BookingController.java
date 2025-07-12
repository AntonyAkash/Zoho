package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingRequestDTO;
import com.example.demo.enums.BookingStatus;
import com.example.demo.enums.LocationPoint;
import com.example.demo.model.Booking;
import com.example.demo.services.BookingService;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // ✅ Create a booking
    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
    	 try {
    	        String customerId = request.getCustomerId();
    	        LocationPoint from = LocationPoint.valueOf(request.getFroms());
    	        LocationPoint to = LocationPoint.valueOf(request.getToo());

    	        if (customerId == null || customerId.isEmpty()) {
    	            return ResponseEntity.badRequest().body("customerId is required");
    	        }

    	        Booking booking = bookingService.createBooking(customerId, from, to);
    	        return ResponseEntity.ok(booking);
    	    } catch (IllegalArgumentException e) {
    	        return ResponseEntity.badRequest().body("Invalid location. Use values A to F.");
    	    } catch (RuntimeException e) {
    	        return ResponseEntity.badRequest().body(e.getMessage());
    	    }
    }

    // ✅ Get all bookings
    @GetMapping("/getAllBookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // ✅ Update booking status (e.g., mark as COMPLETED)
    @PutMapping("/updateBooking")
    public ResponseEntity<?> updateBooking(@RequestBody BookingRequestDTO request) {
        try {
            int bookingId = request.getId();
            BookingStatus status = BookingStatus.valueOf(request.getStatus());

            bookingService.updateBookingStatus(bookingId, status);
            return ResponseEntity.ok("Booking updated to " + status);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Cancel booking
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable int id) {
        try {
            bookingService.cancelBooking(id);
            return ResponseEntity.ok("Booking cancelled");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
