package com.mcgill.vehicle_total_price_a2.service;

import com.mcgill.vehicle_total_price_a2.model.VehicleTotalPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

// This service calculates the final vehicle rental price.
// It retrieves the daily rate from the Vehicle Season Price service, then combines it with the rental duration to calculate the total cost.
@Service
public class VehicleTotalPriceService {

    // URL of the Vehicle Season Price service.
    // This value is loaded from configuration so the service location can change without modifying application code.
    @Value("${season.price.service.url}")
    private String seasonPriceServiceURL;

    // The port number identifies which service instance processed the rental price request.
    @Value("${server.port}")
    private String port;

    // Used to communicate with other REST services.
    private final RestTemplate restTemplate;

    // Creates the REST client used to call the Vehicle Season Price service.
    public VehicleTotalPriceService() {
        this.restTemplate = new RestTemplate();
    }

    // Calculates the total rental price by:
    // 1. Requesting the daily rate from the pricing service.
    // 2. Multiplying the daily rate by the rental duration.
    // 3. Returning the completed rental price response.
    public VehicleTotalPrice calculateTotalPrice(String vehicle, String season, int days) {
        String url = seasonPriceServiceURL + "/price?vehicle=" + vehicle + "&season=" + season;

        // Add authentication information required by the pricing service.
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().setBasicAuth("rental", "rental123");
            return execution.execute(request, body);
        });

        // Retrieve the daily rental rate from the Vehicle Season Price service.
        Map response = restTemplate.getForObject(url, Map.class);

        int dailyRate = (int) response.get("dailyRate");

        // Calculate the final rental cost based on the number of rental days.
        int totalPrice = dailyRate * days;

        // Return the completed response object containing the rental details and calculated price.
        return new VehicleTotalPrice(vehicle, season, days, dailyRate, totalPrice, port);

    }
}
