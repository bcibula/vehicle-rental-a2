package com.mcgill.vehicle_season_price_a2.controller;

import com.mcgill.vehicle_season_price_a2.service.VehicleSeasonPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

// This controller handles requests for vehicle rental prices.
// It receives a vehicle type and season, asks the service layer for the matching daily rate, and returns the result as a JSON response.
@RestController
public class VehicleSeasonPriceController {

    @Autowired
    private VehicleSeasonPriceService service;

    // The configured server port is included in the response so it is clear which service instance handled the request.
    @Value("${server.port}")
    private String port;

    // This endpoint returns the daily rental price for a vehicle in a specific season.
    @GetMapping("/price")
    public ResponseEntity<Map<String, Object>> getPrice(
            @RequestParam String vehicle,
            @RequestParam String season) {

        // The controller delegates the pricing lookup to the service layer.
        // The service contains the logic for finding the correct seasonal rate.
        Integer dailyRate = service.getPrice(vehicle, season);

        // If the requested vehicle or season does not exist in the pricing data, return a 404 response instead of returning an invalid price.
        if (dailyRate == null) {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("error", "No pricing found for vehicle: " + vehicle + " in season: " + season);
            error.put("port", port);
            return ResponseEntity.status(404).body(error);
        }

        // Build the JSON response returned to the client.
        // Include the request details, daily rate, and service port.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("vehicle", vehicle);
        response.put("season", season);
        response.put("dailyRate", dailyRate);
        response.put("port", port);

        return ResponseEntity.ok(response);

    }

}
