package com.mcgill.vehicle_total_price_a2;

// These lines bring in helper tools from Spring Boot framework that we need to run the application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This special marker tells Spring Boot to automatically set up all the configuration we need
@SpringBootApplication
// This declares the main class for our Spring Boot application
public class VehicleTotalPriceA2Application {

	// This is the entry point - the first method that runs when you start the application
	public static void main(String[] args) {
		// This line starts up the Spring Boot application and gets it ready to receive requests
		SpringApplication.run(VehicleTotalPriceA2Application.class, args);
	}

}
