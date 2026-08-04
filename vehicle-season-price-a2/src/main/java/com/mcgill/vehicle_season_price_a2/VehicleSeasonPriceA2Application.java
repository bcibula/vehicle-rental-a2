package com.mcgill.vehicle_season_price_a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This application starts the Vehicle Total Price microservice.
// The service calculates the full rental cost using the daily rate returned by the Vehicle Season Price microservice.
@SpringBootApplication
public class VehicleSeasonPriceA2Application {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSeasonPriceA2Application.class, args);
	}

}
