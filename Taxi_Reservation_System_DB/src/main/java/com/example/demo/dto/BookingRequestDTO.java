package com.example.demo.dto;

public class BookingRequestDTO {
	
	private int id;
	
	private String customerId;

    private String froms;

    private String too;
    
    private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerId() {
		return customerId;
	}

	public BookingRequestDTO() {
		super();
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFroms() {
		return froms;
	}

	public void setFroms(String froms) {
		this.froms = froms;
	}

	public String getToo() {
		return too;
	}

	public void setToo(String too) {
		this.too = too;
	}


}
