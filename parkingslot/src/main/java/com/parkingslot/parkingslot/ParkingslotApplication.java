package com.parkingslot.parkingslot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="{com.parkingslot}")
public class ParkingslotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingslotApplication.class, args);
	}

}
