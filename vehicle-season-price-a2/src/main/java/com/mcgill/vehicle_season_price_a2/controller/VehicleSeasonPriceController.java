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

@RestController
public class VehicleSeasonPriceController {

    @Autowired
    private VehicleSeasonPriceService service;

    @Value("${server.port}")
    private String port;

    @GetMapping("/price")
    public ResponseEntity<Map<String, Object>> getPrice(
            @RequestParam String vehicle,
            @RequestParam String season) {

        Integer dailyRate = service.getPrice(vehicle, season);

        if (dailyRate == null) {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("error", "No pricing found for vehicle: " + vehicle + " in season: " + season);
            error.put("port", port);
            return ResponseEntity.status(404).body(error);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("vehicle", vehicle);
        response.put("season", season);
        response.put("dailyRate", dailyRate);
        response.put("port", port);

        return ResponseEntity.ok(response);


    }


}
