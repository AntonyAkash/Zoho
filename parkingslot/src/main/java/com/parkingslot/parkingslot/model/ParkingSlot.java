package com.parkingslot.parkingslot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkingSlot {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private boolean occupied;
	
	private String vehicleNumber;
	
	private String vehicleType;
	
	private String slotType;
	
	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public ParkingSlot() {
		
	}

	public ParkingSlot(long id, boolean occupied, String vehicleNumber, String vehicleType) {
		super();
		this.id = id;
		this.occupied = occupied;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "ParkingSlot [id=" + id + ", occupied=" + occupied + ", vehicleNumber=" + vehicleNumber
				+ ", vehicleType=" + vehicleType + "]";
	}
	
	

}
