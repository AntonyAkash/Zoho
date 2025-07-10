package com.example.demo.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.BookingStatus;
import com.example.demo.enums.LocationPoint;
import com.example.demo.model.Booking;
import com.example.demo.model.Taxi;
import com.example.demo.repo.BookingRepository;
import com.example.demo.repo.TaxiRepository;
import com.example.demo.utility.FareCalculator;

import jakarta.annotation.PostConstruct;

@Service
public class BookingService {
	@Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @PostConstruct
    public void init() {
        // Add 5 taxis initially at Location A
        for (int i = 0; i < 5; i++) {
            taxiRepository.save(new Taxi(LocationPoint.A));
        }
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(String customerId, LocationPoint from, LocationPoint to) {
        Taxi assignedTaxi = findNearestAvailableTaxi(from);

        if (assignedTaxi == null) {
            throw new RuntimeException("No available taxi nearby for pickup at " + from);
        }

        int distance = from.getDistance(to);
        double fare = FareCalculator.calculateFare(distance);

        Booking booking = new Booking(
                assignedTaxi.getId(),
                customerId,
                from,
                to,
                distance,
                fare
        );

        assignedTaxi.setAvailable(false);
        taxiRepository.save(assignedTaxi);
        return bookingRepository.save(booking);
    }

    public void updateBookingStatus(int bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking ID not found: " + bookingId));

        booking.setStatus(status);
        bookingRepository.save(booking);

        if (status == BookingStatus.COMPLETED || status == BookingStatus.CANCELLED) {
            Taxi taxi = taxiRepository.findById(booking.getTaxiId())
                    .orElseThrow(() -> new RuntimeException("Taxi not found"));

            if (status == BookingStatus.COMPLETED) {
                taxi.setCurrentLocation(booking.getTo());
            }

            taxi.setAvailable(true);
            taxiRepository.save(taxi);
        }
    }

    public void cancelBooking(int bookingId) {
        updateBookingStatus(bookingId, BookingStatus.CANCELLED);
    }

    private Taxi findNearestAvailableTaxi(LocationPoint pickup) {
        List<Taxi> availableTaxis = taxiRepository.findByAvailableTrue();

        return availableTaxis.stream()
                .min(Comparator.comparingInt(t -> t.getCurrentLocation().getDistance(pickup)))
                .orElse(null);
    }
}
