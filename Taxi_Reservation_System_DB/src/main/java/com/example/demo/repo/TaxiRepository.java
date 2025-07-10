package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enums.LocationPoint;
import com.example.demo.model.Taxi;

public interface TaxiRepository extends JpaRepository<Taxi, Integer> {
	
    List<Taxi> findByAvailableTrue();

    List<Taxi> findByAvailableTrueAndCurrentLocation(LocationPoint location);

}
