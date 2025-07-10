package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value= {"com.example"})
public class TaxiReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiReservationSystemApplication.class, args);
	}

}
