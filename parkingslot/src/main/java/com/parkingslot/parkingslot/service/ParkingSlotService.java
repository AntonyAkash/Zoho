package com.parkingslot.parkingslot.service;

import java.util.List;

import com.parkingslot.parkingslot.model.ParkingSlot;

public interface ParkingSlotService {
	
	public List<ParkingSlot> getAllAvailableSlots();
	public String createParkingSlotWithInsertingData(ParkingSlot parkingSlot);
	List<ParkingSlot> getAllSolts();
	String releaseSlot(long slotId);
	String allocateSlot(String vehicleNumber, String vehicleType);

}
