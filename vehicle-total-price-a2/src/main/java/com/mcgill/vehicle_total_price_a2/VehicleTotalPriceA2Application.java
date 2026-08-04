package com.mcgill.vehicle_total_price_a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This application starts the Vehicle Total Price microservice.
// This service calculates the final rental cost by combining the rental duration with the daily rate provided by the Vehicle Season Price service.
@SpringBootApplication
public class VehicleTotalPriceA2Application {

	public static void main(String[] args) {
		SpringApplication.run(VehicleTotalPriceA2Application.class, args);
	}

}
