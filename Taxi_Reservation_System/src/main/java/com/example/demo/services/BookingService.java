package com.example.demo.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.demo.enums.BookingStatus;
import com.example.demo.enums.LocationPoint;
import com.example.demo.model.Booking;
import com.example.demo.model.Taxi;
import com.example.demo.utility.FareCalculator;

@Service
public class BookingService {
	private final List<Taxi> taxis = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final AtomicInteger bookingIdCounter = new AtomicInteger(1);
    private final AtomicInteger taxiIdCounter = new AtomicInteger(1);

    public BookingService() {
        // Initialize 5 taxis at Location A
        for (int i = 0; i < 5; i++) {
            taxis.add(new Taxi(taxiIdCounter.getAndIncrement()));
        }
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public Optional<Booking> getBookingById(int id) {
        return bookings.stream().filter(b -> b.getId() == id).findFirst();
    }

    public Booking createBooking(String customerId, LocationPoint from, LocationPoint to) {
        Taxi assignedTaxi = findNearestAvailableTaxi(from);

        if (assignedTaxi == null) {
            throw new RuntimeException("No available taxi nearby for pickup at " + from);
        }

        int distance = from.getDistance(to);
        double fare = FareCalculator.calculateFare(distance);

        Booking booking = new Booking(
            bookingIdCounter.getAndIncrement(),
            assignedTaxi.getId(),
            customerId,
            from,
            to,
            distance,
            fare
        );

        // Update taxi status and add booking
        assignedTaxi.setAvailable(false);
        bookings.add(booking);

        return booking;
    }

    public void updateBookingStatus(int bookingId, BookingStatus status) {
        Booking booking = getBookingById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking ID not found: " + bookingId));

        booking.setStatus(status);

        if (status == BookingStatus.COMPLETED || status == BookingStatus.CANCELLED) {
            // Find the taxi and mark it as available
            Taxi taxi = taxis.stream()
                    .filter(t -> t.getId() == booking.getTaxiId())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Taxi not found"));

            if (status == BookingStatus.COMPLETED) {
                taxi.setCurrentLocation(booking.getTo());
            }
            taxi.setAvailable(true);
        }
    }

    public void cancelBooking(int bookingId) {
        updateBookingStatus(bookingId, BookingStatus.CANCELLED);
    }

    private Taxi findNearestAvailableTaxi(LocationPoint pickup) {
        return taxis.stream()
                .filter(Taxi::isAvailable)
                .min(Comparator.comparingInt(t -> t.getCurrentLocation().getDistance(pickup)))
                .orElse(null);
    }
}
