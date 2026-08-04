package com.mcgill.config_server_a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// This application provides centralized configuration for the two vehicle-pricing microservices.
// When each service starts, it connects to the Config Server and retrieves its assigned port number.
// Vehicle Total Price also retrieves the URL for Vehicle Season Price.
@EnableConfigServer
@SpringBootApplication
public class ConfigServerA2Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerA2Application.class, args);
	}

}
