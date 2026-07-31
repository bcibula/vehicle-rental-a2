package com.mcgill.config_server_a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// Tells Spring this app is a Config Server, which serves settings to other apps.
@EnableConfigServer
// Tells Spring Boot to auto-set up the app and start everything it needs.
@SpringBootApplication
public class ConfigServerA2Application {

	// This is the main starting point when you run the program.
	public static void main(String[] args) {
		// Starts the Spring Boot application.
		SpringApplication.run(ConfigServerA2Application.class, args);
	}

}
