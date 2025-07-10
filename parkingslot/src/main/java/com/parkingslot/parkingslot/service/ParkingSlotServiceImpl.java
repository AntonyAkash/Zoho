package com.parkingslot.parkingslot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingslot.parkingslot.model.ParkingSlot;
import com.parkingslot.parkingslot.repository.ParkingSlotRepository;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;

	@Override
	public List<ParkingSlot> getAllAvailableSlots() {
		return parkingSlotRepository.findByOccupied(false);
	}
	
	@Override
	public List<ParkingSlot> getAllSolts() {
		return parkingSlotRepository.findAll();
	}
	@Override
	public String releaseSlot(long slotId) {
		ParkingSlot parkingSlot=parkingSlotRepository.findById(slotId)
								.orElseThrow(()->new RuntimeException("Not such Slot...!"));
		parkingSlot.setOccupied(false);
		parkingSlot.setVehicleNumber(null);
		parkingSlotRepository.save(parkingSlot);
		return "Slot is free now";
	}
	@Override
	public String allocateSlot(String vehicleNumber,String vehicleType) {
		parkingSlotRepository.findFirstByOccupiedFalseAndSlotType(vehicleType)
								.map(slot -> {
									slot.setOccupied(true);
									slot.setVehicleNumber(vehicleNumber);
									return parkingSlotRepository.save(slot);
								}).orElseThrow(()->new RuntimeException ("No Available slot"));
		return "Slot is alloted Successfully";
	}

	@Override
	public String createParkingSlotWithInsertingData(ParkingSlot parkingSlot) {
		parkingSlotRepository.save(parkingSlot);
		return "Data inserted Successfully";
	}
	
}
