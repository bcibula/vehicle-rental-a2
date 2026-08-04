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

// This controller receives a vehicle type and season from the request.
// It asks VehicleSeasonPriceService for the daily rental price and then returns either the pricing details or a 404 response.
@RestController
public class VehicleSeasonPriceController {

    @Autowired
    private VehicleSeasonPriceService service;

    // The port is included in the response.
    @Value("${server.port}")
    private String port;

    // This endpoint returns the daily price for a vehicle in a given season.
    @GetMapping("/price")
    public ResponseEntity<Map<String, Object>> getPrice(
            @RequestParam String vehicle,
            @RequestParam String season) {

        // Ask the service layer to find the price.
        Integer dailyRate = service.getPrice(vehicle, season);

        // If no price is found, send back a 404 error and a simple message.
        if (dailyRate == null) {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("error", "No pricing found for vehicle: " + vehicle + " in season: " + season);
            error.put("port", port);
            return ResponseEntity.status(404).body(error);
        }

        // If a price is found, build a response with the details.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("vehicle", vehicle);
        response.put("season", season);
        response.put("dailyRate", dailyRate);
        response.put("port", port);

        return ResponseEntity.ok(response);


    }


}
