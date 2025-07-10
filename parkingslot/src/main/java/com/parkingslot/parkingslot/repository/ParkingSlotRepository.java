package com.parkingslot.parkingslot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkingslot.parkingslot.model.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long>{
	
	List<ParkingSlot> findByOccupied(boolean occupied);
	
	Optional<ParkingSlot> findFirstByOccupiedFalseAndSlotType(String vehicleType);

}
