package com.example.demo.utility;

import org.springframework.stereotype.Component;

@Component
public class FareCalculator {
	public static double calculateFare(int distanceInKm) {
        if (distanceInKm <= 5) return 100;
        return 100 + (distanceInKm - 5) * 10;
    }
}
