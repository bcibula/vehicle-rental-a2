package com.mcgill.config_server_a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerA2Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerA2Application.class, args);
	}

}
