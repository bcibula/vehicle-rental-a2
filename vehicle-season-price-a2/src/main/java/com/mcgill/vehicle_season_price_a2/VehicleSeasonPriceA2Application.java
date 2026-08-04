package com.mcgill.vehicle_season_price_a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This application starts the Vehicle Season Price microservice.
// The service loads the seasonal pricing data and returns the daily rental rate for a given vehicle and season.
@SpringBootApplication
public class VehicleSeasonPriceA2Application {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSeasonPriceA2Application.class, args);
	}

}
