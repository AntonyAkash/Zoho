package com.parkingslot.parkingslot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkingslot.parkingslot.model.ParkingSlot;
import com.parkingslot.parkingslot.service.ParkingSlotService;

@RestController
@RequestMapping("/parkingslot")
public class ParkingSlotController {
	@Autowired
	private ParkingSlotService parkingSlotService;
	
	@GetMapping("/allSlots")
	public List<ParkingSlot> getAllSlots(){
		return parkingSlotService.getAllSolts();
	}
	
	@GetMapping("/available")
	public List<ParkingSlot> getAvailableSlots(){
		return parkingSlotService.getAllAvailableSlots();
	}
	
	@PutMapping("/freeTheSlot/{slotId}")
	public String freeTheSlot(@PathVariable long slotId) {
		return parkingSlotService.releaseSlot(slotId);
	}
	
	@PutMapping("/assign/{vNum}/{vType}")
	public String assignSlot(@PathVariable String vehicleNumber,@PathVariable String vehicleType) {
		return parkingSlotService.allocateSlot(vehicleNumber, vehicleType);
	}
}
