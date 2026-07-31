package com.mcgill.vehicle_total_price_a2.service;

// These are instructions to bring in helper code from other libraries that we need
import com.mcgill.vehicle_total_price_a2.model.VehicleTotalPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

// This marks this class as a "Service" - a helper that handles business logic (Spring framework feature)
@Service
public class VehicleTotalPriceService {

    // This reads the URL from the settings file and stores it in this variable
    // (where the season price service is located)
    @Value("${season.price.service.url}")
    private String seasonPriceServiceURL;

    // This reads the port number from the settings file and stores it
    // (which port this service is running on)
    @Value("${server.port}")
    private String port;

    // This is a helper tool that lets us send requests to other services over the internet
    private final RestTemplate restTemplate;

    // This is the constructor - it runs when a new instance of this class is created
    // It sets up the RestTemplate tool so we can use it
    public VehicleTotalPriceService() {
        this.restTemplate = new RestTemplate();
    }

    // This method takes three pieces of information and calculates the total rental price
    // Parameters: what vehicle (car name), what season, and how many days to rent
    public VehicleTotalPrice calculateTotalPrice(String vehicle, String season, int days) {
        // Build the web address (URL) we need to call the other service with the vehicle and season info
        String url = seasonPriceServiceURL + "/price?vehicle=" + vehicle + "&season=" + season;

        // Add security credentials (username and password) to the request
        // so the other service knows we're allowed to ask for information
        // This is like showing your ID card to prove who you are
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().setBasicAuth("rental", "rental123");
            return execution.execute(request, body);
        });

        // Send a request to the other service and get back the response as a Map
        // The response will contain the daily rate (price per day) as a collection of data
        Map response = restTemplate.getForObject(url, Map.class);

        // Extract the daily rate (cost per day) from the response data
        int dailyRate = (int) response.get("dailyRate");

        // Calculate the total price by multiplying the daily rate by the number of days
        int totalPrice = dailyRate * days;

        // Create and return a new VehicleTotalPrice object that contains all the information
        // including the vehicle, season, days, daily rate, total price, and which port we're on
        return new VehicleTotalPrice(vehicle, season, days, dailyRate, totalPrice, port);

    }
}
