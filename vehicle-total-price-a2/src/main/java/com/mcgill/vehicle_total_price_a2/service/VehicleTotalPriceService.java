package com.mcgill.vehicle_total_price_a2.service;

import com.mcgill.vehicle_total_price_a2.model.VehicleTotalPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class VehicleTotalPriceService {

    @Value("${season.price.service.url}")
    private String seasonPriceServiceURL;

    @Value("${server.port}")
    private String port;

    private final RestTemplate restTemplate;

    public VehicleTotalPriceService() {
        this.restTemplate = new RestTemplate();
    }

    public VehicleTotalPrice calculateTotalPrice(String vehicle, String season, int days) {
        // build the URL to call vehicle-season-price-a2
        String url = seasonPriceServiceURL + "/price?vehicle=" + vehicle + "&season=" + season;

        // call vehicle-season-price-a2 with Basic Auth credentials
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().setBasicAuth("rental", "rental123");
            return execution.execute(request, body);
        });

        // get the response as a Map
        Map response = restTemplate.getForObject(url, Map.class);

        int dailyRate = (int) response.get("dailyRate");
        int totalPrice = dailyRate * days;

        return new VehicleTotalPrice(vehicle, season, days, dailyRate, totalPrice, port);

    }





}
