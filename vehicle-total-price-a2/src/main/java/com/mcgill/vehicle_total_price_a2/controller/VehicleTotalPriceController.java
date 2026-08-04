package com.mcgill.vehicle_total_price_a2.controller;

import com.mcgill.vehicle_total_price_a2.model.VehicleTotalPrice;
import com.mcgill.vehicle_total_price_a2.service.VehicleTotalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// This controller provides the API endpoint for calculating the final vehicle rental price.
// It receives rental details from the client and delegates the calculation to the service layer.
@RestController
public class VehicleTotalPriceController {

    @Autowired
    private VehicleTotalPriceService service;

    // Calculates the total rental cost based on:
    // - vehicle type
    // - rental season
    // - number of rental days
    // The service handles the pricing lookup and calculation logic.
    @GetMapping("/total-price")
    public ResponseEntity<VehicleTotalPrice> getTotalPrice(
            @RequestParam String vehicle,
            @RequestParam String season,
            @RequestParam int days) {

        VehicleTotalPrice result = service.calculateTotalPrice(vehicle, season, days);

        // Return the completed rental price response to the client.
        return ResponseEntity.ok(result);
    }

}
